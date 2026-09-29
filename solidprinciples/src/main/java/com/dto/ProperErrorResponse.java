package com.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public record ProperErrorResponse(

    int statusCode,
    String errorMessage,
   HttpStatus errorType,
    String uriPath,
    LocalDateTime errorTime
) {

}
