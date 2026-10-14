package com.dto;

import lombok.Data;

@Data 
public class UpdateUserRequest {


    
    private String firstName;

    private String lastName;

    private short age;

    private String gender;

    private String mobileNumber;

    private String email;

    private String password;

    private int chooseAccountType;
}
