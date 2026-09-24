package com.axc.solidprinciples.service.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.axc.solidprinciples.constant.AccountStatus;
import com.axc.solidprinciples.constant.AccountType;
import com.axc.solidprinciples.mappers.UserMapper;
import com.axc.solidprinciples.model.Account;
import com.axc.solidprinciples.model.User;
import com.axc.solidprinciples.service.IUserService;
import com.axc.solidprinciples.utility.HelperClass;
import com.dto.UserRequest;
import com.dto.UserResponse;

@Service 
public class UserServiceImpl implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
   
    @Override
    public UserResponse createUser(UserRequest userRequest) {

        logger.info("Creating user account with request {}", userRequest);
       
        if (userRequest == null) {
            throw new IllegalArgumentException("User request cannot be null");
        }
       
          User user = UserMapper.mapToUser(userRequest);
          user.setUserId(HelperClass.generateId());
          user.setAccount(createAccountForUser(user));

          logger.info("User account created successfully with userId {}", user.getUserId());

        return UserMapper.mapToUserResponse(user);

    }


    private Account createAccountForUser(User user) {
     
        logger.info("creating account for user {}", user);

        Account account = new Account();
        account.setAccountId(HelperClass.generateId());
        account.setAccountNumber(HelperClass.generateAccountNumber());

        logger.info("user is choosing account type user {}", user);
        AccountType type = HelperClass.chooseAccountType(user.getChooseAccountType());
        account.setAccountType(type);
        account.setAccountStatus(AccountStatus.CREATED);
        account.setLedgerBalance(BigDecimal.valueOf(0));
        account.setBalance(BigDecimal.valueOf(0));
        account.setAvailableBalance(BigDecimal.valueOf(0));
        account.setUser(user);

        logger.info("Account created successfully for user {}", user.getUserId());
        return account; 
     
    }


}
