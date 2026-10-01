package com.axc.solidprinciples.utility;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;



@Component 
public class AcountNumberGeneration {


    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private static final int ACCOUNT_NUMBER_LENGTH = 14;

    private static final String ACCOUNT_NUMBER_PREFIX = "AXC";

   


    private AcountNumberGeneration() {
        
    }


    public static String generateAccountNumber() {

        
        StringBuilder accountNumber = new StringBuilder(ACCOUNT_NUMBER_PREFIX);

        if (ACCOUNT_NUMBER_PREFIX.length() >= ACCOUNT_NUMBER_LENGTH) {
            throw new IllegalStateException("Account number prefix is too long. It must be shorter than the total account number length.");
        }

        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH - ACCOUNT_NUMBER_PREFIX.length(); i++) {

            accountNumber.append(SECURE_RANDOM.nextInt(10));
        }

        return accountNumber.toString();
    }




}
