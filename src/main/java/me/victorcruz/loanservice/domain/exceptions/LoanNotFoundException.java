package me.victorcruz.loanservice.domain.exceptions;

/**
 * Loan not found exception
 *
 * Thrown when a loan was not found in the persistence storage
 */
public class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException(String id) {
        super(String.format("Loan with id `%s` was not found", id));
    }
}
