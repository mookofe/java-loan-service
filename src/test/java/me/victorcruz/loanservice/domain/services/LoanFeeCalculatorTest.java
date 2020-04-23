package me.victorcruz.loanservice.domain.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import me.victorcruz.loanservice.domain.models.LoanType;

public class LoanFeeCalculatorTest {
    private LoanFeeCalculator service;

    @BeforeEach
    public void setup() {
        this.service = new LoanFeeCalculator();
    }

    @Test
    public void testCalculateStudentFee() {
        // Setup
        LoanType loanType = LoanType.STUDENT;

        // When
        Double fee = service.calculate(loanType);

        // Then
        assertEquals(0, fee);
    }

    @Test
    public void testCalculateAutoFee() {
        // Setup
        LoanType loanType = LoanType.AUTO;

        // When
        Double fee = service.calculate(loanType);

        // Then
        assertEquals(500, fee);
    }

    @Test
    public void testCalculatePersonalFee() {
        // Setup
        LoanType loanType = LoanType.PERSONAL;

        // When
        Double fee = service.calculate(loanType);

        // Then
        assertEquals(750, fee);
    }

    @Test
    public void testCalculateMortgageFee() {
        // Setup
        LoanType loanType = LoanType.MORTGAGE;

        // When
        Double fee = service.calculate(loanType);

        // Then
        assertEquals(1500, fee);
    }
}
