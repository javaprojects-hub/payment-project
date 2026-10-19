package com.axc.solidprinciples.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.axc.solidprinciples.model.Account;

import jakarta.persistence.LockModeType;

@Repository 
public interface AccountRepository extends JpaRepository<Account, UUID>{

    boolean existsByAccountNumber(String accountNumber);
    boolean existsByAccountId(String accountId);

    @Query ("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Account> findByAccountNumber(@Param ("accountNumber") String accountNumber);

    @Query("SELECT a FROM Account a WHERE a.accountId = :accountId")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Account> findByAccountId(@Param("userId") String userId);


    

    
}
