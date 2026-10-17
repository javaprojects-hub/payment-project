package com.dto;

public record UserAccountResponse(
    String userId,
    String firstName,
    String lastName,
    String gender,
    String email,
    String mobileNumber,
    String status,
    String accountId,
    String accountNumber,
    String accountType,
    String accountStatus
) {
}