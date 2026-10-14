package com.axc.solidprinciples.utility;

public class PasswordValidate {


    public static boolean isValidPassword(String password) {
        
        // Check if the password length is between 8 and 12 characters
        if (password.length() <= 8 || password.length() >= 12) {
            return false;
        }

        // Check if the password contains at least one uppercase letter
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }

        // Check if the password contains at least one lowercase letter
        if (!password.matches(".*[a-z].*")) {
            return false;
        }

        // Check if the password contains at least one digit
        if (!password.matches(".*\\d.*")) {
            return false;
        }

        // Check if the password contains at least one special character
        if (!password.matches(".*[!@#$%^&*()].*")) {
            return false;
        }

        return true;
    }
}
