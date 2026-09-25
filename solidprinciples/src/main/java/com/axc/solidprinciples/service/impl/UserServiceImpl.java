package com.axc.solidprinciples.service.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.axc.solidprinciples.constant.AccountStatus;
import com.axc.solidprinciples.constant.AccountType;
import com.axc.solidprinciples.exception.MobileNumberAlreadyExists;
import com.axc.solidprinciples.mappers.UserMapper;
import com.axc.solidprinciples.model.Account;
import com.axc.solidprinciples.model.User;
import com.axc.solidprinciples.repository.AccountRepository;
import com.axc.solidprinciples.repository.UserRepository;
import com.axc.solidprinciples.service.IUserService;
import com.axc.solidprinciples.utility.HelperClass;
import com.dto.UserRequest;
import com.dto.UserResponse;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor  
public class UserServiceImpl implements IUserService {


    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
   
    @Override
    @Transactional
    public UserResponse createUser(UserRequest userRequest) {

        logger.info("Creating user account with request {}", userRequest);
       
        if (userRequest == null) {
            throw new IllegalArgumentException("User request cannot be null");
        }

       if (userRepository.existsByMobileNumber(userRequest.getMobileNumber())) {
        
        throw new MobileNumberAlreadyExists("Mobile Number already existed with :" + userRequest.getMobileNumber());
       }
       
          User user = UserMapper.mapToUser(userRequest);
          user.setUserId(HelperClass.generateId());
          user.setChooseAccountType(userRequest.getChooseAccountType());
          user.setAccount(createAccountForUser(user));
          
         User savedUser = userRepository.save(user);

          logger.info("User account created successfully with userId {}", user.getUserId());

        return UserMapper.mapToUserResponse(savedUser);

    }


    private Account createAccountForUser(User user) {
     
        logger.info("creating account for user {}", user);

       

        Account account = new Account();
       String accountId = HelperClass.generateId();
       account.setAccountId(accountId);
       String accountNumber = HelperClass.generateAccountNumber();

       if (accountRepository.existsByAccountNumberAndAccountId(accountNumber, accountId)) {
        logger.warn("Already exisited account number {}, accountId {}", accountNumber, accountId);
        throw new IllegalStateException("Account Number and Account Id is already existed in the database");
       }
        account.setAccountNumber(accountNumber);
       logger.info("user is choosing account type user {}", user);
        AccountType type = HelperClass.chooseAccountType(user.getChooseAccountType());
        account.setAccountType(type);
        account.setAccountStatus(AccountStatus.CREATED);
        account.setLedgerBalance(BigDecimal.valueOf(0));
        account.setBalance(BigDecimal.valueOf(0));
        account.setAvailableBalance(BigDecimal.valueOf(0));
        account.setUser(user);

       Account savedAccount = accountRepository.save(account);
        logger.info("Account created successfully for user {}", account);
        return savedAccount; 
     
    }


}
