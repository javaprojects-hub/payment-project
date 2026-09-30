package com.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;


@Getter  
public class ActivateUserRequest {

    
    @NotBlank(message = "userId cannot be blank")
    private String userId;

    @NotBlank (message = "mobileNumber cannot be blank")
    private String mobileNumber;

    @NotNull(message = "activateCode cannot be null")
    @Min (value = 100000, message = "activateCode must be 6 digits")
    @Max(value = 999999, message = "activateCode must be 6 digits")
    private Integer activateCode;
}
