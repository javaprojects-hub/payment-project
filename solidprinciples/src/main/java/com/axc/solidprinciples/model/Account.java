package com.axc.solidprinciples.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.axc.solidprinciples.constant.AccountStatus;
import com.axc.solidprinciples.constant.AccountType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table(
    name = "account",
    indexes = {
        @Index(name = "idx_account_id", columnList = "account_id"),
        @Index(name = "idx_account_number", columnList = "account_number")
    }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(name = "account_id", unique = true, nullable = false)
    private String accountId;

    @NotNull (message = "Account number cannot be null")
    @Column(name = "account_number", unique = true, nullable = false)
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @NotNull(message = "Balance cannot be null")
    private BigDecimal balance;

    @NotNull(message = "Available balance cannot be null")
    private BigDecimal availableBalance;

    @NotNull(message = "Ledger balance cannot be null")
    private BigDecimal ledgerBalance;

    @OneToOne(mappedBy = "account") 
    private User user;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}