package com.axc.solidprinciples.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table (name = "payment")
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @NotNull(message = "Payment ID cannot be null") 
    private String paymentId;

    @NotNull(message = "Account number cannot be null")
    private String accountNumber;

    @NotNull(message = "To account number cannot be null")
    private String toAccountNumber;
    
    @NotNull(message = "Amount cannot be null")
    @Min(value = 1, message = "Amount must be greater than or equal to one rupee")
    @Max(value = 1000000, message = "Amount must be less than or equal to one million rupees") 
    private BigDecimal amount;


    @NotNull(message = "Before balance cannot be null")
    private BigDecimal beforeBalance;

    @NotNull 
    private BigDecimal afterBalance;

   @NotNull(message = "Payment type cannot be null")
   private String paymentType;

   @NotNull(message = "Payment status cannot be null")
   private String paymentStatus;

   @CreationTimestamp 
   private LocalDateTime createdAt;

   @UpdateTimestamp 
    private LocalDateTime updatedAt;
}
