package com.axc.solidprinciples.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) 
public class MobileNumberAlreadyExists extends RuntimeException {

    public  MobileNumberAlreadyExists(String message) {
      
        super(message);
    }

}
