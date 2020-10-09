package me.victorcruz.loanservice.domain.repositories;

import org.springframework.stereotype.Repository;
import me.victorcruz.loanservice.domain.models.Payment;
import org.springframework.data.repository.CrudRepository;

/**
 * Loan repository interface
 */
@Repository
public interface PaymentRepository extends CrudRepository<Payment, String> {
}
