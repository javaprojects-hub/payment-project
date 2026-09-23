package com.axc.solidprinciples.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class Account {

    @Id 
    @GeneratedValue(strategy = GenerationType.TABLE)
    private UUID id;

    @NotNull(message = "Account ID cannot be null")
    @Column (name = "account_id", unique = true, nullable = false)
    private String accountId;

    @NotNull(message = "Account number cannot be null")
    @Column (name = "account_number", unique = true, nullable = false)
    private String accountNumber;

    @NotNull(message = "Account type cannot be null")
    private AccountType accountType;

    private AccountStatus accountStatus;

    @NotNull(message = "Balance cannot be null")
    private BigDecimal balance;

    @NotNull(message = "Available balance cannot be null")
    private BigDecimal availableBalance;

    @NotNull(message = "Ledger balance cannot be null")
    private BigDecimal ledgerBalance;

    @CreationTimestamp 
    private LocalDateTime createdAt;

    @UpdateTimestamp 
    private LocalDateTime updatedAt;

}
