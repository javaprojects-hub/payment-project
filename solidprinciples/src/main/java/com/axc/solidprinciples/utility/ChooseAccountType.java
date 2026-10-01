package com.axc.solidprinciples.utility;

import org.springframework.stereotype.Component;

import com.axc.solidprinciples.constant.AccountType;

@Component 
public class ChooseAccountType {


    private ChooseAccountType() {
        // private constructor to prevent instantiation
    }

    public static AccountType chooseAccountType(int choice) {

        switch (choice) {

            case 1 :
                return AccountType.SAVINGS;
            case 2 : 
                return AccountType.CURRENT;
            case 3 :
                return AccountType.SALARY_ACCOUNT;
            case 4 :
                return AccountType.FIXED_DEPOSIT;
            case 5 :
                return AccountType.RECURRING_DEPOSIT;
            default :
                throw new IllegalArgumentException("Invalid choice. Please select a valid account type.");

        }
       
    }
}
