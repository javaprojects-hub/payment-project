package com.axc.solidprinciples.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.axc.solidprinciples.constant.AccountStatus;
import com.axc.solidprinciples.constant.AccountType;
import com.axc.solidprinciples.constant.UserStatus;
import com.axc.solidprinciples.exception.MobileNumberAlreadyExists;
import com.axc.solidprinciples.exception.OtpExpiredException;
import com.axc.solidprinciples.exception.UserNotFoundException;
import com.axc.solidprinciples.mappers.UserMapper;
import com.axc.solidprinciples.model.Account;
import com.axc.solidprinciples.model.User;
import com.axc.solidprinciples.repository.AccountRepository;
import com.axc.solidprinciples.repository.UserRepository;
import com.axc.solidprinciples.service.IKycService;
import com.axc.solidprinciples.service.IUserService;
import com.axc.solidprinciples.utility.AcountNumberGeneration;
import com.axc.solidprinciples.utility.ActivationCodeGenerator;
import com.axc.solidprinciples.utility.ChooseAccountType;
import com.axc.solidprinciples.utility.PasswordValidate;
import com.axc.solidprinciples.utility.UniqueIdGenerator;
import com.dto.ActivateUserRequest;
import com.dto.UpdateUserRequest;
import com.dto.UserRequest;
import com.dto.UserResponse;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor 
public class UserServiceImpl implements IUserService {


    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    private IKycService iKycService;

    private static final Map<Integer, Integer> CACHE_FOR_ACTIVATION_CODE = new HashMap<>();

    private  PasswordEncoder passwordEncoder;

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

        if (!PasswordValidate.isValidPassword(userRequest.getPassword())) {
            logger.warn("Invalid password format for user request: {} = ", userRequest);
            throw new IllegalArgumentException("Invalid password format");
        }

          User user = UserMapper.mapToUser(userRequest);
          user.setUserId(uniqueUserId());

          user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
          user.setPassword(userRequest.getPassword());
          user.setChooseAccountType(userRequest.getChooseAccountType());
          user.setAccount(createAccountForUser(user));
          
         User savedUser = userRepository.save(user);

         iKycService.createkycForUser(savedUser);

          logger.info("User account created successfully with userId {}", user.getUserId());

         int code =  ActivationCodeGenerator.generateActivationCode(); 

          CACHE_FOR_ACTIVATION_CODE.put(uniqueKey(), code);

         System.out.println("Activation Code for ::"  + code);

        return UserMapper.mapToUserResponse(savedUser);

    }


    @Override
	public String activateUser(ActivateUserRequest activateRequest) {

        logger.info("processing the activate userstatus = {}", activateRequest);

	


       User user = userRepository.findByUserId(activateRequest.getUserId())

            .orElseThrow(() -> new RuntimeException("User not found"));

    if (!user.getMobileNumber().equals(activateRequest.getMobileNumber())) {
        throw new RuntimeException("Mobile number does not match");
    }

     if (ActivationCodeGenerator.isExpiredActivationCode(activateRequest.getActivateCode())) {

        logger.error("Activation code is expired for userId = {}", activateRequest.getUserId());
         
        throw new OtpExpiredException("OTP has expired");
    }

    if (!CACHE_FOR_ACTIVATION_CODE.containsValue(activateRequest.getActivateCode())) {

        throw new RuntimeException("invalid activation code");
    }

   
    if (user.getUserStatus() != UserStatus.CREATED) {
        throw new RuntimeException("User is already active");
    }

    user.setUserStatus(UserStatus.ACTIVE);
    
    logger.info("userstatus is activated to active = {} ", user.getUserId());

     Account account = user.getAccount();

     account.setAccountStatus(AccountStatus.ACTIVE);

     User savedUser  = userRepository.save(user);

    return "User account is activated successfully";

	}



    private Account createAccountForUser(User user) {
     
        logger.info("creating account for user {}", user);

        Account account = new Account();
       
       account.setAccountId(uniqueAccountId());
       account.setAccountNumber(uniqueAccountNumber());
       logger.info("user is choosing account type user {}", user);
       AccountType type = ChooseAccountType.chooseAccountType(user.getChooseAccountType());
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

    
    @Override
    public UserResponse getUserByUserId(String userId) {

        logger.info("Fetching user details by userId = {}", userId);

        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with userId: " + userId));
    
        logger.info("User details fetched successfully for userId = {}", userId);

        return UserMapper.mapToUserResponse(user);
       
    }

     @Override
    public UserResponse updateUser(String userId, UpdateUserRequest updateUserRequest) {
       
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }

        if (updateUserRequest == null) {
            throw new IllegalArgumentException("Update request cannot be null");
        }

        User user = userRepository.findByUserIdForUpdate(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with userId: " + userId));

        if (updateUserRequest.getFirstName() != null) {
            user.setFirstName(updateUserRequest.getFirstName());
        }

        if (updateUserRequest.getLastName() != null) {
            user.setLastName(updateUserRequest.getLastName());
        }

        if (updateUserRequest.getAge() > 0) {
            user.setAge(updateUserRequest.getAge());
        }

        if (updateUserRequest.getGender() != null) {
            user.setGender(updateUserRequest.getGender());
        }

        if (updateUserRequest.getMobileNumber() != null || !updateUserRequest.getMobileNumber().isEmpty()) {

            if (userRepository.existsByMobileNumber(updateUserRequest.getMobileNumber())) {
                throw new MobileNumberAlreadyExists("Mobile Number already existed with :" + updateUserRequest.getMobileNumber());
            }

            user.setMobileNumber(updateUserRequest.getMobileNumber());
        }
        
        User updatedUser = userRepository.save(user);
        return UserMapper.mapToUserResponse(updatedUser);
    }


    private int uniqueKey() {

    return (int) (System.currentTimeMillis() % 10000);
    }


    private String uniqueAccountNumber() {

        String accountNumber = AcountNumberGeneration.generateAccountNumber();

        while (accountRepository.existsByAccountNumber(accountNumber)) {
            accountNumber = AcountNumberGeneration.generateAccountNumber();
        }
        return accountNumber;
    }

    private String uniqueAccountId() {

        String uniqueId = UniqueIdGenerator.generateUniqueId();

        while (accountRepository.existsByAccountId(uniqueId) ) {
            uniqueId = UniqueIdGenerator.generateUniqueId();
        }
        return uniqueId;
    }

    private String uniqueUserId() {

        String uniqueId = UniqueIdGenerator.generateUniqueId();

        while (userRepository.existsByUserId(uniqueId) ) {
            uniqueId = UniqueIdGenerator.generateUniqueId();
        }
        return uniqueId;
    }


   


	
}
