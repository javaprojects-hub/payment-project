package com.dto;

import java.math.BigDecimal;

import lombok.Getter;

@Getter 
public class WithdrawRequest {

    private String password;

    private String idempotencyKey;

    private String accountNumber;

    private String accountType;

    private BigDecimal amount;

    
}
