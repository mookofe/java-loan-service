package me.victorcruz.loanservice.domain.services;

import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import me.victorcruz.loanservice.domain.models.Loan;
import me.victorcruz.loanservice.domain.models.LoanType;
import me.victorcruz.loanservice.domain.repositories.LoanRepository;
import me.victorcruz.loanservice.domain.exceptions.LoanNotFoundException;

public class LoanServiceTest {
    @Test
    public void testStore() {
        // Setup
        Loan loan = new Loan();

        AprCalculator aprCalculator = mock(AprCalculator.class);
        when(aprCalculator.calculate(loan)).thenReturn(10.0);

        LoanRepository loanRepository = mock(LoanRepository.class);
        when(loanRepository.save(loan)).thenReturn(loan);

        LoanService loanService = new LoanService(aprCalculator, loanRepository);

        // When
        Loan createdLoan = loanService.store(loan);

        // Then
        assertEquals(createdLoan.getApr(), 10.0);
    }

    @Test
    public void testGetById() {
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

        AprCalculator aprCalculator = mock(AprCalculator.class);
        LoanRepository loanRepository = mock(LoanRepository.class);
        when(loanRepository.findById("5ea10a64f3e2ba7ee9d088f3")).thenReturn(Optional.of(loan));

        LoanService loanService = new LoanService(aprCalculator, loanRepository);

        // When
        Loan returnedLoan = loanService.getById("5ea10a64f3e2ba7ee9d088f3");

        // Then
        assertEquals(returnedLoan, loan);
    }

    @Test
    public void testGetByIdNotFound() {
        // Setup
        AprCalculator aprCalculator = mock(AprCalculator.class);
        LoanRepository loanRepository = mock(LoanRepository.class);
        when(loanRepository.findById("5ea10a64f3e2ba7ee9d088f3")).thenReturn(Optional.empty());

        LoanService loanService = new LoanService(aprCalculator, loanRepository);

        // When
        LoanNotFoundException exception = assertThrows(
                LoanNotFoundException.class,
                () -> loanService.getById("5ea10a64f3e2ba7ee9d088f3")
        );

        // Then
        assertEquals("Loan with id `5ea10a64f3e2ba7ee9d088f3` was not found", exception.getMessage());
    }
}
