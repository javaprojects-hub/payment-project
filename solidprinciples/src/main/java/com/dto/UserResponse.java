package com.dto;


public record UserResponse(
    String userId,
    String firstName,
    String lastName,
    short age,
    String gender,
    String mobileNumber,
    String email
) {

}
