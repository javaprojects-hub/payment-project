package com.axc.solidprinciples.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dto.ProperErrorResponse;


import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice 
public class GlobalExceptionHandler {


    @ExceptionHandler(AccountAlreadyExisted.class) 
    public ResponseEntity<ProperErrorResponse> handleAccountAlreadyExisted(AccountAlreadyExisted ex, HttpServletRequest request) {

        ProperErrorResponse properErrorResponse = new ProperErrorResponse(
           HttpStatus.BAD_REQUEST.value(),
           ex.getMessage(),
           HttpStatus.CONFLICT,
           request.getRequestURI(),
           LocalDateTime.now()

        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(properErrorResponse);

    }


    @ExceptionHandler(MobileNumberAlreadyExists.class)
    public ResponseEntity<ProperErrorResponse> handleMobileNumberAlreadyExisted(MobileNumberAlreadyExists ex, HttpServletRequest request) {

        return new ResponseEntity<>(
        new ProperErrorResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                HttpStatus.CONFLICT,
                request.getRequestURI(),
                LocalDateTime.now()
        ),
        HttpStatus.CONFLICT);
    }

}
