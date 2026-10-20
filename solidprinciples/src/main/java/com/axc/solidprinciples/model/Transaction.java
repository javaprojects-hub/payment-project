package com.axc.solidprinciples.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.axc.solidprinciples.constant.TransactionStatus;
import com.axc.solidprinciples.constant.TransactionType;
import com.axc.solidprinciples.constant.PaymentType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity 
@Table(name = "transaction",

   indexes =  { @Index ( name = "idx_transaction_account", columnList = "account_id"),
                
                @Index (name = "isx_transaction_reference_id", columnList = "transaction_reference_id")
}
)
@Data 
public class Transaction {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "transaction_reference_id", unique = true, updatable = false)
    private String transactionReferenceId;

    private String accountNumber;

    private BigDecimal amount;

    private BigDecimal beforeBalance;

    private BigDecimal afterBalance; 

    private String fromAccount;

    private String toAccount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionsStatus;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;


    private String failureReason;

    @NotNull(message = "idempotencykey cannot be null") 
    @Column(name = "idempotency_key", unique = true, updatable = false) 
    private String idempotencyKey;

    @Column(name = "created_at",updatable = false)
    @CreationTimestamp 
    private LocalDateTime createdAt;

    @Column(name = "update_at", insertable = false)
    @UpdateTimestamp 
    private LocalDateTime updatedAt;




}
