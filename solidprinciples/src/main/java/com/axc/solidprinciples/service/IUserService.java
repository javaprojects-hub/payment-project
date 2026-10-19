package com.axc.solidprinciples.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dto.ActivateUserRequest;
import com.dto.UpdateUserRequest;
import com.dto.UserAccountResponse;
import com.dto.UserRequest;
import com.dto.UserResponse;

public interface IUserService {

    UserResponse createUser(UserRequest userRequest);

    String activateUser(ActivateUserRequest activateRequest);
    
    UserResponse getUserByUserId(String userId);
    
    UserResponse updateUser(String userId, UpdateUserRequest updateUserRequest);

    UserResponse getUserByMobileNumber(String mobileNumber);

     void deactivateUser();

     Page<UserAccountResponse> getAllUsersWithAccounts(Pageable pageable);
 
}
