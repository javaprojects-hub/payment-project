package com.axc.solidprinciples.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.axc.solidprinciples.model.Payment;


@Repository 
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    boolean existsByAccountNumber(String accountNumber);
}
