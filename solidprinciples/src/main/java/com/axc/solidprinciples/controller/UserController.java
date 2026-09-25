package com.axc.solidprinciples.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.axc.solidprinciples.service.IUserService;
import com.dto.UserRequest;
import com.dto.UserResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController 
@RequestMapping("/api/v1/user") 
@AllArgsConstructor 
public class UserController {

  private final static Logger logger = LoggerFactory.getLogger(UserController.class);

  private final IUserService iUserService;
  
  

  @PostMapping
  public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {

      logger.info("initiating the controller class for userRequest {}", userRequest);

     UserResponse userResponse = iUserService.createUser(userRequest);

     return new ResponseEntity<>(userResponse, HttpStatus.CREATED);

  }


}
