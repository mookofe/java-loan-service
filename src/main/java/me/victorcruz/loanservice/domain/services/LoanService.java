package me.victorcruz.loanservice.domain.services;

import org.springframework.stereotype.Service;
import me.victorcruz.loanservice.domain.models.Loan;
import me.victorcruz.loanservice.domain.models.Payment;
import me.victorcruz.loanservice.domain.repositories.LoanRepository;
import me.victorcruz.loanservice.domain.exceptions.LoanNotFoundException;
import me.victorcruz.loanservice.domain.exceptions.LoanFeeNotFoundException;

/**
 * Loan Service
 *
 * Provides consumers the ability to handle loans
 */
@Service
public class LoanService {
    private AprCalculator aprCalculator;
    private LoanRepository loanRepository;

    public LoanService(AprCalculator aprCalculator, LoanRepository loanRepository) {
        this.aprCalculator = aprCalculator;
        this.loanRepository = loanRepository;
    }

    /**
     * Store loan in the persistence storage
     */
    public Loan store(Loan loan) throws LoanFeeNotFoundException {
        Double apr = this.aprCalculator.calculate(loan);
        loan.setApr(apr);

        return this.loanRepository.save(loan);
    }

    /**
     * Get Loan by id
     */
    public Loan getById(String id) {
        return this.loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException(id));
    }
}
