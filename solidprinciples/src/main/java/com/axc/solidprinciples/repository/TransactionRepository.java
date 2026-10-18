package com.axc.solidprinciples.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.axc.solidprinciples.constant.TransactionStatus;
import com.axc.solidprinciples.constant.TransactionType;
import com.axc.solidprinciples.model.Transaction;

@Repository 
public interface TransactionRepository extends JpaRepository<Transaction, UUID>{

    Optional<Transaction> findByTransactionReferenceId(String transactionReferenceId);


    @Query(""" 
            SELECT COALECSE(SUM(t.amount), 0) FROM Transaction t
            WHEREt t.account.accountId = :accountId
            AND t.transactionStatus = :status
            AND t.transactionType = :type
            AND t.transactionDate >= :start
            AND t.transactionDate < :end
        
          """)
    BigDecimal getDailyWithdrawlTotal(@Param ("accountId") String accountId,
        @Param("status") TransactionStatus status,
        @Param("type") TransactionType type,
        @Param("start") LocalDateTime start,
        @Param("end") LocalDateTime end);
      

    
   // total amount of account logic 
@Query("""
    SELECT COALESCE(SUM(t.amount), 0)
    FROM Transaction t
    WHERE t.account.accountId = :accountId
      AND t.transactionStatus IN :statuses
    """)
BigDecimal getTotalAmountByAccountIdAndStatus(
        @Param("accountId") String accountId,
        @Param("statuses") Collection<TransactionStatus> statuses
);
 
   

}
