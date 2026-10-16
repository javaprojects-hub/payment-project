package com.axc.solidprinciples.service;

import com.dto.ActivateUserRequest;
import com.dto.UpdateUserRequest;
import com.dto.UserRequest;
import com.dto.UserResponse;

public interface IUserService {

    UserResponse createUser(UserRequest userRequest);

    String activateUser(ActivateUserRequest activateRequest);
    
    UserResponse getUserByUserId(String userId);
    
    UserResponse updateUser(String userId, UpdateUserRequest updateUserRequest);
 
}
