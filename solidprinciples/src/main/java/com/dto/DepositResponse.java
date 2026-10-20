package com.dto;

import java.math.BigDecimal;

import lombok.Getter;

@Getter 
public class DepositResponse {

    private String accountNumber;

    private BigDecimal amount;

    
}
