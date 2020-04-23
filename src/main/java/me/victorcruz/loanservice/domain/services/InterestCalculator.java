package me.victorcruz.loanservice.domain.services;

import org.springframework.stereotype.Service;

/**
 * Interest Calculator Service
 *
 * Responsible for calculating interest on a loan
 */
@Service
public class InterestCalculator {
    public Double calculate(Double loanAmount, Double rate, int term) {
        Double timeInYears = this.getYearsFromDays(term);

        return loanAmount * rate * timeInYears / 100;
    }

    private Double getYearsFromDays(int days) {
        return days / 365.00;
    }
}
