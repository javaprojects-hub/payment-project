package com.axc.solidprinciples.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axc.solidprinciples.model.IdemoptencyRecord;

public interface IdempotencyRepository extends JpaRepository<IdemoptencyRecord, Long>{

   Optional<IdemoptencyRecord> findByAccountNumberAndOperationAndIdempotencyKey(String accountNumber, String operation, String idempotencyKey);
}
