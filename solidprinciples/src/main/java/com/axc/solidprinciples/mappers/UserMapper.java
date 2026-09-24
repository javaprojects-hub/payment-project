package com.axc.solidprinciples.mappers;

import org.springframework.stereotype.Component;

import com.axc.solidprinciples.model.User;
import com.dto.UserRequest;
import com.dto.UserResponse;

@Component 
public class UserMapper {


    public static UserResponse mapToUserResponse(User user) {
        return new UserResponse(
            user.getUserId(),
            user.getFirstName(),
            user.getLastName(),
            user.getAge(),
            user.getGender(),
            user.getMobileNumber(),
            user.getEmail()
        );
    }




    public static User mapToUser(UserRequest userRequest) {

       User user = new User();
       user.setFirstName(userRequest.getFirstName());
       user.setLastName(userRequest.getLastName());
       user.setAge(userRequest.getAge());
       user.setGender(userRequest.getGender());
       user.setMobileNumber(userRequest.getMobileNumber());
        user.setEmail(userRequest.getEmail());

       return user;
    }
}
