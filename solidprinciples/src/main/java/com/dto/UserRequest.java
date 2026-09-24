package com.dto;

import lombok.Data;

@Data 
public class UserRequest {

    private String firstName;

    private String lastName;

    private short age;

    private String gender;

    private String mobileNumber;

    private String email;

    private int chooseAccountType;

}
