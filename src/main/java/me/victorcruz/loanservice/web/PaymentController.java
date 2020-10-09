package me.victorcruz.loanservice.web;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;
import me.victorcruz.loanservice.domain.models.Payment;
import me.victorcruz.loanservice.web.requests.PaymentCreate;
import me.victorcruz.loanservice.domain.services.PaymentService;

@RestController
@RequestMapping("/api/v1/loans/{loanId}/balance-history?oderBy=paymentDate, orientation=desc")
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping()
    public Payment store(@Valid @RequestBody PaymentCreate paymentCreate, @PathVariable String loanId ) {
        Payment payment = paymentCreate.buildPayment();

        return this.paymentService.store(loanId, payment);
    }
}
