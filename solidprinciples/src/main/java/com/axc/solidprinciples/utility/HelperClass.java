package com.axc.solidprinciples.utility;



public class HelperClass {

// generate a account number

private static final String ACCOUNT_NUMBER_PREFIX = "AXC";

private static final int ACCOUNT_NUMBER_LENGTH = 14;

private HelperClass() {
    // private constructor to prevent instantiation
}

public  static String generateAccountNumber() {

    String accountNumber = ACCOUNT_NUMBER_PREFIX + System.nanoTime() % 10000000000L; // Get last 10 digits of timestamp

     return ACCOUNT_NUMBER_PREFIX + String.format("%0" + (ACCOUNT_NUMBER_LENGTH - ACCOUNT_NUMBER_PREFIX.length()) + "d", 
     Long.parseLong(accountNumber.substring(ACCOUNT_NUMBER_PREFIX.length())));
}


// generate a payment id

public static String generatePaymentId() {

    String paymentId = "PAY" + System.nanoTime() % 10000000000L; // Get last 10 digits of timestamp

    return paymentId;
}



}
