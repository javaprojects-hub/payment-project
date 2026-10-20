package com.axc.solidprinciples.utility;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

import com.dto.DepositRequest;

public final class RequestHashUtil {

    private RequestHashUtil() {

    }


    public static String calculateHash(DepositRequest depositRequest) {

        try {

            MessageDigest messageDigest = MessageDigest.getInstance("SHA_256");
              
           
            String canonicalRequest =
                    normalize(depositRequest.getAccountNumber()) + "|" +
                    normalize(depositRequest.getAccountType()) + "|" +
                    normalizeAmount(depositRequest.getDepositAmount());

           byte[] hashBytes = messageDigest.digest(

                canonicalRequest.getBytes(StandardCharsets.UTF_8)
            );
        return HexFormat.of().formatHex(hashBytes);
        }
        catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(
                    "SHA-256 algorithm is unavailable", e
            );
        }
        

    }


    private static String normalize(String value) {

        if (value == null) {

            throw new IllegalArgumentException( "Required request field cannot be null");
        }

        return value.trim();

    }

     private static String normalizeAmount(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than zero"
            );
        }

        return amount.stripTrailingZeros().toPlainString();
    }
}
