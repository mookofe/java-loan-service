package me.victorcruz.loanservice.domain.services;

import org.springframework.stereotype.Service;
import me.victorcruz.loanservice.domain.models.Loan;
import me.victorcruz.loanservice.domain.exceptions.LoanFeeNotFoundException;

/**
 * APR Calculator Service
 *
 * Responsible of calculating APR for a given loan
 */
@Service
public class AprCalculator {
    private LoanFeeCalculator feeCalculator;
    private InterestCalculator interestCalculator;

    public AprCalculator(LoanFeeCalculator feeCalculator, InterestCalculator interestCalculator) {
        this.feeCalculator = feeCalculator;
        this.interestCalculator = interestCalculator;
    }

    public Double calculate(Loan loan) throws LoanFeeNotFoundException {
        Double fees = this.feeCalculator.calculate(loan.getLoanType());
        Double interest = this.interestCalculator.calculate(
                loan.getLoanAmount(),
                loan.getRate(),
                loan.getTerm()
        );

        return (((fees + interest) / loan.getLoanAmount()) / loan.getTerm()) * 365 * 100;
    }
}
