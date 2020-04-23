package me.victorcruz.loanservice.web.requests;

import lombok.Data;
import lombok.Builder;
import java.util.Date;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import me.victorcruz.loanservice.domain.models.Loan;
import me.victorcruz.loanservice.domain.models.LoanType;
import me.victorcruz.loanservice.web.validators.ValueOfEnum;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanCreate {
    @NotNull
    private String name;

    @NotNull
    private String ssn;

    @NotNull
    private Date dateOfBirth;

    @NotNull
    @Min(1)
    private Double loanAmount;

    @NotNull
    @Min(0)
    private Double rate;

    @NotNull
    @ValueOfEnum(enumClass = LoanType.class)
    private String loanType;

    @NotNull
    @Min(1)
    private int term;

    public Loan buildLoanFromRequest() {
        Loan loan = new Loan();
        loan.setName(this.name);
        loan.setSsn(this.ssn);
        loan.setDateOfBirth(this.dateOfBirth);
        loan.setLoanAmount(this.loanAmount);
        loan.setRate(this.rate);
        loan.setLoanType(LoanType.valueOf(this.loanType));
        loan.setTerm(this.term);

        return loan;
    }
}
