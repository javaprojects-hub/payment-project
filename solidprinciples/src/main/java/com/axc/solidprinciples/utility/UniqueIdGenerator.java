package com.axc.solidprinciples.utility;

import org.springframework.stereotype.Component;

@Component 
public class UniqueIdGenerator {

    private UniqueIdGenerator() {
        // private constructor to prevent instantiation
    }

    public static String generateUniqueId() {

        StringBuilder uniqueId = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            uniqueId.append((int) (Math.random() * 10));
        }

        return uniqueId.toString();
    }

}
