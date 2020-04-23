package me.victorcruz.loanservice.domain.services;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InterestCalculatorTest {
    @Test
    public void calculate() {
        // Setup
        InterestCalculator service = new InterestCalculator();

        // When
        Double interest = service.calculate(1000.00, 10.0, 365);

        // Then
        assertEquals(100.0, interest);
    }
}
