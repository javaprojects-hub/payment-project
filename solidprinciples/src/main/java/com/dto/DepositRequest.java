package com.dto;

import java.math.BigDecimal;

import lombok.Getter;

@Getter 
public class DepositRequest {

    private String password;

   private String idempotencyKey;

   private String accountNuber;

   private String accountType;

   private BigDecimal withdrawlAmount;
}
