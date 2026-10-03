package com.axc.solidprinciples.utility;

import org.springframework.stereotype.Component;

@Component 
public class ActivationCodeGenerator {

    private ActivationCodeGenerator() {
        // private constructor to prevent instantiation
    }

    public static int generateActivationCode() {

        StringBuilder activationCode = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            activationCode.append((int) (Math.random() * 10));
        }

        return Integer.parseInt(activationCode.toString());
    }


}
