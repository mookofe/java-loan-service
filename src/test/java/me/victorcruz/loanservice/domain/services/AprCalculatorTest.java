package me.victorcruz.loanservice.domain.services;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import me.victorcruz.loanservice.domain.models.Loan;
import me.victorcruz.loanservice.domain.models.LoanType;

public class AprCalculatorTest {
    @Test
    public void testCalculate() {
        // Setup
        Loan loan = new Loan();
        loan.setLoanAmount(1000.00);
        loan.setRate(10.0);
        loan.setLoanType(LoanType.STUDENT);
        loan.setTerm(365);

        LoanFeeCalculator loanFeeCalculator = mock(LoanFeeCalculator.class);
        when(loanFeeCalculator.calculate(LoanType.STUDENT)).thenReturn(0.00);

        InterestCalculator interestCalculator = mock(InterestCalculator.class);
        when(interestCalculator.calculate(1000.00, 10.0, 365)).thenReturn(100.0);

        AprCalculator aprCalculator = new AprCalculator(loanFeeCalculator, interestCalculator);

        // When
        Double apr = aprCalculator.calculate(loan);

        // Then
        assertEquals(10, Math.round(apr));
    }
}
