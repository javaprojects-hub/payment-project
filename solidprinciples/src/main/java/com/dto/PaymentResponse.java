package com.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component 
@AllArgsConstructor 
@NoArgsConstructor 
public class PaymentResponse {

    private String paymentId;

    private BigDecimal amount;

    private String accountNumber;

    private String toAccountNumber;

    private String paymentType;

    private String paymentStatus;

    private LocalDateTime createdAt;


}
