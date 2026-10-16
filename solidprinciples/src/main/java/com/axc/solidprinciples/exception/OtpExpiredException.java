package com.axc.solidprinciples.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.BAD_REQUEST, reason = "OTP has expired") 
public class OtpExpiredException extends RuntimeException {

    public OtpExpiredException(String message) {
        super(message);
    }

}
