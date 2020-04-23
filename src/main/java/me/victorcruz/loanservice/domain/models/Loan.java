package me.victorcruz.loanservice.domain.models;

import lombok.Data;
import java.util.Date;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Domain model that represents a Loan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "loans")
public class Loan {
    @Id
    private String id;
    private String name;
    private String ssn;
    private Date dateOfBirth;
    private Double loanAmount;
    private Double rate;
    private LoanType loanType;
    private int term;
    private Double apr;
}
