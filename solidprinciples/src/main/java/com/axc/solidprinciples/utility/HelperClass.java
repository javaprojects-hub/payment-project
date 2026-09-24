package com.axc.solidprinciples.utility;

import java.security.SecureRandom;

public class HelperClass {

// generate a account number

private static final String ACCOUNT_NUMBER_PREFIX = "AXC";

private static final int ACCOUNT_NUMBER_LENGTH = 14;

private final static SecureRandom secureRandom = new SecureRandom();

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


// generate a payment id

public static String generatePaymentId() {

    String paymentId = "PAY" + System.nanoTime() % 10000000000L; // Get last 10 digits of timestamp

    return paymentId;
}



}
