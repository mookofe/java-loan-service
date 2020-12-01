package me.victorcruz.loanservice.domain.models;

import lombok.Data;
import lombok.Builder;
import java.util.Date;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * Domain model that represents a Loan Payment
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    private String id;
    private String loanId;
    private Double amount;
    private Date effectiveDate;
}
