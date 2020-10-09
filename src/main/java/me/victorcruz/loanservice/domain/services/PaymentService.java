package me.victorcruz.loanservice.domain.services;

import org.springframework.stereotype.Service;
import me.victorcruz.loanservice.domain.models.Loan;
import me.victorcruz.loanservice.domain.models.Payment;
import me.victorcruz.loanservice.domain.repositories.PaymentRepository;

@Service
public class PaymentService {
    private LoanService loanService;
    private PaymentRepository paymentRepository;
    private BalanceDecreaser balanceDecreaser;

    public PaymentService(LoanService loanService, PaymentRepository paymentRepository, BalanceDecreaser balanceDecreaser) {
        this.loanService = loanService;
        this.paymentRepository = paymentRepository;
        this.balanceDecreaser = balanceDecreaser;
    }

    public Payment store(String loanId, Payment payment) {
        Loan loan = this.loanService.getById(loanId);
        payment.setLoanId(loan.getId());
        this.paymentRepository.save(payment);



        this.balanceDecreaser.decreaseBalance(loan, payment);

        return payment;
    }
}
