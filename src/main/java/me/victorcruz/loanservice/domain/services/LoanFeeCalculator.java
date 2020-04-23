package me.victorcruz.loanservice.domain.services;

import org.springframework.stereotype.Service;
import me.victorcruz.loanservice.domain.models.LoanType;
import me.victorcruz.loanservice.domain.exceptions.LoanFeeNotFoundException;

/**
 * Loan Fee Calculator
 *
 * Responsible of calculating the fees for a given loan
 */
@Service
public class LoanFeeCalculator {
    public Double calculate(LoanType loanType) throws LoanFeeNotFoundException {
        if (loanType == LoanType.STUDENT) {
            return 0d;
        }

        if (loanType == LoanType.AUTO) {
            return 500.00;
        }

        if (loanType == LoanType.PERSONAL) {
            return 750.00;
        }
        if (loanType == LoanType.MORTGAGE) {
            return 1500.00;
        }

        throw new LoanFeeNotFoundException(loanType);
    }
}
