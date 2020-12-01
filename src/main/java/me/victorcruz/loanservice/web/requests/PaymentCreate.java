package me.victorcruz.loanservice.web.requests;

import lombok.Data;
import me.victorcruz.loanservice.domain.models.Payment;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class PaymentCreate {
    @Min(0)
    @NotNull
    private Double amount;
    private Date effectiveDate = new Date();

    public Payment buildPayment() {
        return Payment.builder()
                .amount(this.amount)
                .effectiveDate(this.effectiveDate)
                .build();
    }
}
