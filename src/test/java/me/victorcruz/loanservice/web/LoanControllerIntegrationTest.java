package me.victorcruz.loanservice.web;

import java.util.Date;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import static org.mockito.Mockito.*;
import org.springframework.http.MediaType;
import org.mockito.invocation.InvocationOnMock;
import me.victorcruz.loanservice.domain.models.Loan;
import org.springframework.test.web.servlet.MockMvc;
import me.victorcruz.loanservice.domain.models.LoanType;
import org.springframework.boot.test.mock.mockito.MockBean;
import me.victorcruz.loanservice.domain.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import me.victorcruz.loanservice.domain.exceptions.LoanNotFoundException;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest
public class LoanControllerIntegrationTest {
    @Autowired
    private MockMvc mockedMvc;

    @MockBean
    private LoanService loanService;

    @Test
    public void testCreateLoan() throws Exception {
        // Setup
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Loan loan = (Loan)invocationOnMock.getArgument(0);
                loan.setId("5ea10a64f3e2ba7ee9d088f3");
                loan.setApr(10.0);

                return loan;
            }
        }).when(this.loanService).store(any());

        String payload = "{\n" +
                "    \"name\": \"John Doe\",\n" +
                "    \"ssn\": \"123-11-1133\",\n" +
                "    \"dateOfBirth\": \"2000-12-30\",\n" +
                "    \"loanAmount\": 1000,\n" +
                "    \"rate\": 10,\n" +
                "    \"loanType\": \"STUDENT\",\n" +
                "    \"term\": 365\n" +
                "}";
        // Then
        this.mockedMvc.perform(post("/api/v1/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.dateOfBirth").value("2000-12-30T00:00:00.000+0000"))
                .andExpect(jsonPath("$.loanAmount").value(1000.0))
                .andExpect(jsonPath("$.rate").value(10.0))
                .andExpect(jsonPath("$.loanType").value("STUDENT"))
                .andExpect(jsonPath("$.term").value(365))
                .andExpect(jsonPath("$.apr").exists());
    }

    @Test
    public void testCreateLoanWitEmptyBody() throws Exception {
        this.mockedMvc.perform(post("/api/v1/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.error").exists())
                .andExpect(jsonPath("$.error").value("Unprocessable Entity"))
                .andExpect(jsonPath("$.status").exists())
                .andExpect(jsonPath("$.status").value(422))
                .andExpect(jsonPath("$.errors").exists())
                .andExpect(jsonPath("$.errors").isArray());
    }

    @Test
    public void testGetLoan() throws Exception {
        // Setup
        Loan loan = new Loan(
                "5ea10a64f3e2ba7ee9d088f3",
                "John Doe",
                "123-11-1234",
                new Date(),
                1000.0,
                10.0,
                LoanType.PERSONAL,
                365,
                10.0
        );

        when(this.loanService.getById("5ea10a64f3e2ba7ee9d088f3")).thenReturn(loan);

        // Then
        this.mockedMvc.perform(get("/api/v1/loans/5ea10a64f3e2ba7ee9d088f3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("5ea10a64f3e2ba7ee9d088f3"))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.dateOfBirth").exists())
                .andExpect(jsonPath("$.loanAmount").value(1000.0))
                .andExpect(jsonPath("$.rate").value(10.0))
                .andExpect(jsonPath("$.loanType").value("PERSONAL"))
                .andExpect(jsonPath("$.term").value(365))
                .andExpect(jsonPath("$.apr").value(10.0));
    }

    @Test
    public void testGetNonExistingLoan() throws Exception {
        // Setup
        when(this.loanService.getById(anyString()))
                .thenThrow(new LoanNotFoundException("5ea10a64f3e2ba7ee9d088f3"));

        // Then
        this.mockedMvc.perform(get("/api/v1/loans/non-existing-loan")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
