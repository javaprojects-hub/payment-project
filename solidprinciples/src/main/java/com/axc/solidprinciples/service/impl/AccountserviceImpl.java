package com.axc.solidprinciples.service.impl;


import javax.security.auth.login.AccountNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.axc.solidprinciples.constant.UserStatus;
import com.axc.solidprinciples.exception.UserNotFoundException;
import com.axc.solidprinciples.model.Account;
import com.axc.solidprinciples.model.User;
import com.axc.solidprinciples.repository.AccountRepository;
import com.axc.solidprinciples.repository.UserRepository;
import com.axc.solidprinciples.service.AccountService;
import com.dto.DepositRequest;
import com.dto.WithdrawRequest;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor  
public class AccountserviceImpl implements AccountService {


    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private static final Integer MAX_ATTEMPTS = 3;

    private static final Logger logger = LoggerFactory.getLogger(AccountserviceImpl.class);

    @Override
    public void deposit(DepositRequest depositRequest) {

        logger.info("initiating the deposit method with = {}", depositRequest);

        if ( depositRequest == null) {

            throw new IllegalArgumentException("deposit request cannot be null");
        }
        
    }

    @Override
    public void withdraw(WithdrawRequest withdrawRequest) {

        logger.info("initiating the withdrawl for user = {} ", withdrawRequest);

        if (withdrawRequest == null) {

            throw new IllegalArgumentException("withdraw request caanot be null");
        }
        
    }

     
private boolean verifyPassword( String accountNumber, String rawPassword) {

    logger.info("verifying the password | {}");

    if (accountNumber == null || accountNumber.isBlank()
            || rawPassword == null || rawPassword.isBlank()) {
        throw new IllegalArgumentException(
                "Account number or password cannot be blank");
    }

    Account account = accountRepository
            .findByAccountNumber(accountNumber)
            .orElseThrow(() ->
                    new com.axc.solidprinciples.exception.AccountNotFoundException(
      
                      "Account not found"));

     logger.info("Account Deatils fetched successfully = {}", account);

    User user = account.getUser();

    
    if (user.isTransactionLocked()
            || user.getUserStatus() == UserStatus.BLOCKED) {
        throw new IllegalStateException(
                "User is blocked from performing transactions");
    }



    
    if (!passwordEncoder.matches(
            rawPassword, user.getPasswordHash())) {

       
        int attempts = user.getFailedPasswordAttempts() + 1;

        user.setFailedPasswordAttempts(attempts);

        
        if (attempts >= MAX_ATTEMPTS) {

    logger.info("Too Many Attempts reached = {}", attempts);
            user.setTransactionLocked(true);
            user.setUserStatus(UserStatus.BLOCKED);
        }

        userRepository.save(user);

        return false;
    }

    
    user.setFailedPasswordAttempts(0);
    userRepository.save(user);

    return true;
}
  

}