package com.axc.solidprinciples.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axc.solidprinciples.model.Kyc;

@Repository 
public interface KycRepository extends JpaRepository<Kyc, Long> {


    Optional<Kyc> findByUserId(Long id);
}
