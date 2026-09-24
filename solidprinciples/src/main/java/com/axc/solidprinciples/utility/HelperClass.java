package com.axc.solidprinciples.utility;

import java.security.SecureRandom;

import com.axc.solidprinciples.constant.AccountType;

public class HelperClass {

// generate a account number

private static final String ACCOUNT_NUMBER_PREFIX = "AXC";

private static final int ACCOUNT_NUMBER_LENGTH = 14;

private final static SecureRandom secureRandom = new SecureRandom();

pri

private HelperClass() {
    // private constructor to prevent instantiation
}


public  static String generateAccountNumber() {

   StringBuilder accountNumber = new StringBuilder(ACCOUNT_NUMBER_PREFIX);

   if (ACCOUNT_NUMBER_PREFIX.length() >= ACCOUNT_NUMBER_LENGTH) {

     throw new IllegalStateException("Account number prefix is too long. It must be shorter than the total account number length.");
   }

    for (int i = 0; i < ACCOUNT_NUMBER_LENGTH - ACCOUNT_NUMBER_PREFIX.length(); i++) {
      accountNumber.append(secureRandom.nextInt(10));
   }

   

   return accountNumber.toString();
}


// generate a user id

public static String generateId() {

  StringBuilder userId = new StringBuilder();

  for (int i = 0; i < 8; i++) {
    userId.append(secureRandom.nextInt(0, 10));
  }

  return userId.toString();
}

// generate a payment id

public static String generatePaymentId() {

    String paymentId = "PAY" + System.nanoTime() % 10000000000L; // Get last 10 digits of timestamp

    return paymentId;
}

// implement account type based on user option if he chooses 1 for savings, 2 for current, 3 for salary account
 
  public static AccountType chooseAccountType(int option) {

    switch(option) {

      case 1 : 
       return AccountType.SAVINGS;
      
      
      case 2:
        return AccountType.CURRENT;

      case 3:
         return AccountType.SALARY_ACCOUNT;

      default:
        throw new IllegalArgumentException("Invalid account type option");
       

    }
  }
   

}
