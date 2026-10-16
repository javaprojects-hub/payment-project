package com.axc.solidprinciples.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.axc.solidprinciples.service.IUserService;
import com.dto.ActivateUserRequest;
import com.dto.UpdateUserRequest;
import com.dto.UserRequest;
import com.dto.UserResponse;

import jakarta.validation.Valid;


@RestController 
@RequestMapping("/api/v1/user") 
public class UserController {

  private final static Logger logger = LoggerFactory.getLogger(UserController.class);

  private final IUserService iUserService;
  
  public UserController(IUserService iUserService) {
      this.iUserService = iUserService;
  }

  @PostMapping
  public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {

      logger.info("initiating the controller class for userRequest {}", userRequest);

     UserResponse userResponse = iUserService.createUser(userRequest);

     return new ResponseEntity<>(userResponse, HttpStatus.CREATED);

  }


  @PatchMapping
  public ResponseEntity<String> activateUser(@Valid @RequestBody ActivateUserRequest userRequest) {

      logger.info("initiating the controller class for userRequest {}", userRequest);

     String result = iUserService.activateUser(userRequest);

     return new ResponseEntity<>(result, HttpStatus.OK);

  }

  @GetMapping("/{userId}/details")
  public ResponseEntity<UserResponse> getUserByUserId(@PathVariable String userId) {

      logger.info("Fetching user details by userId = {}", userId);

      UserResponse userResponse = iUserService.getUserByUserId(userId);

      return new ResponseEntity<>(userResponse, HttpStatus.OK);

}

@PatchMapping("/{userId}/update")
public ResponseEntity<UserResponse> updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserRequest updateUserRequest) {
    logger.info("Updating user details for userId = {}", userId);

    UserResponse updatedUserResponse = iUserService.updateUser(userId, updateUserRequest);

    return new ResponseEntity<>(updatedUserResponse, HttpStatus.OK);


}

}
