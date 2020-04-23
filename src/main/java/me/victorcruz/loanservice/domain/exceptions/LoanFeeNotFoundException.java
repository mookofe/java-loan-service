package me.victorcruz.loanservice.domain.exceptions;

import me.victorcruz.loanservice.domain.models.LoanType;

/**
 * Loan fee not found exception
 *
 * Thrown when a fee was not found for the given loan type
 */
public class LoanFeeNotFoundException extends RuntimeException {
    public LoanFeeNotFoundException(LoanType loanType) {
        super(String.format("Fee for loan type `%s` was not found", loanType));
    }
}
