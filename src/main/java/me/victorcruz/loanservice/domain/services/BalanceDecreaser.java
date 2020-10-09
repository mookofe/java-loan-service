package me.victorcruz.loanservice.domain.services;

import me.victorcruz.loanservice.domain.models.Loan;
import me.victorcruz.loanservice.domain.models.Payment;
import me.victorcruz.loanservice.domain.repositories.LoanRepository;
import org.springframework.stereotype.Service;

@Service
public class BalanceDecreaser {
    LoanRepository loanRepository;

    public BalanceDecreaser(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public void decreaseBalance(Loan loan, Payment payment) {
        Double newBalance = loan.getLoanAmount() - payment.getAmount();
        loan.setLoanAmount(newBalance);

        this.loanRepository.save(loan);
    }
}
