package com.axc.solidprinciples.service.impl;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.axc.solidprinciples.exception.AccountAlreadyExisted;
import com.axc.solidprinciples.mappers.PaymentMapper;
import com.axc.solidprinciples.repository.PaymentRepository;
import com.axc.solidprinciples.service.IPaymentService;
import com.dto.Paymentrequest;

import lombok.AllArgsConstructor;



@Service
 @AllArgsConstructor 
public class PaymentServiceImpl implements IPaymentService {

private final PaymentRepository paymentRepository;

private final PaymentMapper paymentMapper;

private static final Logger logger = LoggerFactory.getLogger("PaymentServiceImpl.class");


public void createPayment(Paymentrequest paymentRequest) {

    logger.info("creating payment with account number: {}", paymentRequest.getAccountNumber());
    
     if ()

     if (paymentRepository.existsByAccountNumber(paymentRequest.getAccountNumber()) 
        && paymentRepository.existsByAccountNumber(paymentRequest.getToAccountNumber())) {

        logger.warn("Account already exists with account number: {}", paymentRequest.getAccountNumber());
        throw new AccountAlreadyExisted("Account already exists with account number: " + paymentRequest.getAccountNumber());
   }

      

}


    @Override
    public void processPayment() {
       
    }

}
