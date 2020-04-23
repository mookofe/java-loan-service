package me.victorcruz.loanservice.web;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;
import me.victorcruz.loanservice.domain.models.Loan;
import me.victorcruz.loanservice.web.requests.LoanCreate;
import me.victorcruz.loanservice.domain.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Loan controller
 *
 * Entry point for Loan resources
 */
@RestController()
@RequestMapping("/api/v1/loans")
public class LoanController {
    private LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping()
    public Loan create(@Valid @RequestBody LoanCreate loanCreate) {
        Loan loan = loanCreate.buildLoanFromRequest();

        return this.loanService.store(loan);
    }

    @GetMapping("/{id}")
    public Loan view(@PathVariable String id) {
        return this.loanService.getById(id);
    }
}
