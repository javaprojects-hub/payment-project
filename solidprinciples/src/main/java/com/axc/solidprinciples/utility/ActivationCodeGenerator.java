package com.axc.solidprinciples.utility;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component 
public class ActivationCodeGenerator {

    private static final Map<Integer, LocalDateTime> CACHE_FOR_ACTIVATION_CODE = new java.util.concurrent.ConcurrentHashMap<>();

    private ActivationCodeGenerator() {
        // private constructor to prevent instantiation
    }

    public static int generateActivationCode() {

        int activationCode = 100000 + (int) (Math.random() * 900000);

       while (CACHE_FOR_ACTIVATION_CODE.containsKey(activationCode)) {

            activationCode = 100000 + (int) (Math.random() * 900000);
        }

     CACHE_FOR_ACTIVATION_CODE.put(activationCode, LocalDateTime.now());

        return activationCode;
    }


    public static boolean isExpiredActivationCode(int activationCode) {
   
     LocalDateTime createdAt = CACHE_FOR_ACTIVATION_CODE.get(activationCode);

    if (createdAt == null) {
        return true; // Missing code is invalid
    }

    if (!LocalDateTime.now().isBefore(createdAt.plusMinutes(2))) {

        CACHE_FOR_ACTIVATION_CODE.remove(activationCode); 
        return false; // Code is expired
    }

    CACHE_FOR_ACTIVATION_CODE.remove(activationCode); 

    return  true; 

    }
}


