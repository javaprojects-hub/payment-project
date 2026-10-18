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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "transaction")
public class Transaction {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "transaction_id", unique = true, updatable = false)
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

    @Column(name = "created_at",updatable = false)
    @CreationTimestamp 
    private LocalDateTime createdAt;

    @Column(name = "update_at", insertable = false)
    @UpdateTimestamp 
    private LocalDateTime updatedAt;




}
