package com.axc.solidprinciples.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dto.ProperErrorResponse;


import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice 
public class GlobalExceptionHandler {



    private static final Map<String, String> map = new HashMap();



   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<ProperErrorResponse> handleMethodArgumentValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {


    
    ex.getBindingResult()
       .getFieldErrors()
          .forEach( error -> map.put(error.getField(), error.getDefaultMessage()));
    
    return new ResponseEntity<>(
        new ProperErrorResponse(HttpStatus.BAD_REQUEST.value(),
         map.toString(), HttpStatus.BAD_REQUEST, request.getRequestURI(), LocalDateTime.now()),
             
           HttpStatus.BAD_REQUEST);



    }


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
