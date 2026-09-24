package com.axc.solidprinciples.service;

import com.dto.UserRequest;
import com.dto.UserResponse;

public interface IUserService {

    UserResponse createUser(UserRequest userRequest);
 
}
