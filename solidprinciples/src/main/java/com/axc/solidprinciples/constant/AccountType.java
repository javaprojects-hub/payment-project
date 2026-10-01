package com.axc.solidprinciples.constant;

public enum AccountType {

     SAVINGS,
     CURRENT,
     FIXED_DEPOSIT,
     RECURRING_DEPOSIT,
     SALARY_ACCOUNT;


    private AccountType() {
        // private constructor to prevent instantiation
    }
   
}
