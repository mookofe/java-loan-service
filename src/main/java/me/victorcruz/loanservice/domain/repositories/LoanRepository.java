package me.victorcruz.loanservice.domain.repositories;

import org.springframework.stereotype.Repository;
import me.victorcruz.loanservice.domain.models.Loan;
import org.springframework.data.repository.CrudRepository;

/**
 * Loan repository interface
 */
@Repository
public interface LoanRepository extends CrudRepository<Loan, String> {
}
