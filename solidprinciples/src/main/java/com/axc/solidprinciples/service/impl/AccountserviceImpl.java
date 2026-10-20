package com.axc.solidprinciples.service.impl;


import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.axc.solidprinciples.constant.IdempotencyStatus;
import com.axc.solidprinciples.constant.UserStatus;
import com.axc.solidprinciples.exception.UserNotFoundException;
import com.axc.solidprinciples.model.Account;
import com.axc.solidprinciples.model.IdemoptencyRecord;
import com.axc.solidprinciples.model.Transaction;
import com.axc.solidprinciples.model.User;
import com.axc.solidprinciples.repository.AccountRepository;
import com.axc.solidprinciples.repository.IdempotencyRepository;
import com.axc.solidprinciples.repository.TransactionRepository;
import com.axc.solidprinciples.repository.UserRepository;
import com.axc.solidprinciples.service.AccountService;
import com.axc.solidprinciples.utility.RequestHashUtil;
import com.dto.DepositRequest;
import com.dto.WithdrawRequest;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor  
public class AccountserviceImpl implements AccountService {


    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    private final IdempotencyRepository idempotencyRepository;

    private PasswordEncoder passwordEncoder;

    private final TransactionRepository transactionRepository;

    private static final int MAX_ATTEMPTS = 3;

    private static final Logger logger = LoggerFactory.getLogger(AccountserviceImpl.class);

    @Override
    public void deposit(DepositRequest depositRequest) {

        logger.info("initiating the deposit method with deposit = {}", depositRequest);

        if (depositRequest == null) {

            throw new IllegalArgumentException("depositRequest is cannot be null");
        }

        if (depositRequest.getIdempotencyKey() == null || depositRequest.getIdempotencyKey().isBlank()) {

             throw new IllegalArgumentException("Idempotency key is required");
        }

        if (depositRequest.getDepositAmount() == null || depositRequest.getDepositAmount().signum() <= 0) {

            throw new IllegalArgumentException("Deposit amount must be greater than zero");
        }

       

       logger.info("Fetching the account details {} =" , depositRequest.getAccountNumber());

     Account account =  accountRepository.findByAccountNumber(depositRequest.getAccountNumber())
                                             .orElseThrow(() -> new com.axc.solidprinciples.exception.AccountNotFoundException("Account Not Found with " + depositRequest.getAccountNumber()));
                                     
        if (!verifyPassword(depositRequest.getAccountNumber(), depositRequest.getPassword())) {

          throw new IllegalArgumentException("password is invalid, please check your password and try again");
       }

      Optional<Transaction> existingTransaction = transactionRepository.findByIdempotencyKey(depositRequest.getIdempotencyKey());

      String requestHash = RequestHashUtil.calculateHash(depositRequest);

        String key = depositRequest.getIdempotencyKey();

      var existing =  idempotencyRepository
                .findByAccountNumberAndOperationAndIdempotencyKey(
                        account.getAccountNumber(),
                        "DEPOSIT",
                        key);

        if (existing.isPresent()) {
            IdemoptencyRecord record = existing.get();

            if (!record.getRequestHash().equals(requestHash)) {
                throw new IllegalArgumentException(
                        "Idempotency key reused with different request");
            }

            if (record.getStatus() == IdempotencyStatus.SUCCESS) {
                return ;
            }

            throw new IllegalStateException(
                    "A request with this key is already in progress");
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