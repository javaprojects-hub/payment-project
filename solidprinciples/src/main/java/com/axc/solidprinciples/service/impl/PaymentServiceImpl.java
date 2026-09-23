package com.axc.solidprinciples.service.impl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.axc.solidprinciples.mappers.PaymentMapper;
import com.axc.solidprinciples.repository.PaymentRepository;
import com.axc.solidprinciples.service.IPaymentService;

import lombok.AllArgsConstructor;



@Service
@AllArgsConstructor 
public class PaymentServiceImpl implements IPaymentService {

private final PaymentRepository paymentRepository;

private final PaymentMapper paymentMapper;

private static final Logger logger = LoggerFactory.getLogger("PaymentServiceImpl.class");



    @Override
    public void processPayment() {
       
    }
}

