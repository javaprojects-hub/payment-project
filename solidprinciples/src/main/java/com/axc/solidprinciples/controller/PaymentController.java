package com.axc.solidprinciples.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axc.solidprinciples.service.IPaymentService;



@RestController 
@RequestMapping("/api/payments")
public class PaymentController {

    private final IPaymentService iPaymentService;

    public PaymentController(IPaymentService iPaymentService) {
        this.iPaymentService = iPaymentService;
    }
}
