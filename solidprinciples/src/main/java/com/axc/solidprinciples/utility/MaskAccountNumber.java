package com.axc.solidprinciples.utility;

import org.springframework.stereotype.Component;

@Component 
public class MaskAccountNumber {


public static String maskAccountNumber(String accountNumber) {

    if (accountNumber == null || accountNumber.length() < 4) {
        throw new IllegalArgumentException("Account number must be at least 4 characters long");
    }

    int length = accountNumber.length();
    String maskedPart = "*".repeat(length - 4);
    String visiblePart = accountNumber.substring(length - 4);
    return maskedPart + visiblePart;

}

}
