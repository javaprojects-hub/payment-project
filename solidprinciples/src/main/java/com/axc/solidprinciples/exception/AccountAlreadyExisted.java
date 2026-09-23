package com.axc.solidprinciples.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AccountAlreadyExisted extends RuntimeException{

    public AccountAlreadyExisted(String message) {
        super(message);
    }
}
