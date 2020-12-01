package me.victorcruz.loanservice.domain.services;

import org.springframework.stereotype.Service;
import me.victorcruz.loanservice.domain.models.Loan;
import me.victorcruz.loanservice.domain.models.Payment;
import me.victorcruz.loanservice.domain.repositories.LoanRepository;

@Service
public class BalanceAdjuster {
    LoanRepository loanRepository;

    public BalanceAdjuster(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public void decreaseBalance(Loan loan, Payment payment) {
        Double newBalance = loan.getLoanAmount() - payment.getAmount();
        loan.setLoanAmount(newBalance);

        this.loanRepository.save(loan);
    }
}
