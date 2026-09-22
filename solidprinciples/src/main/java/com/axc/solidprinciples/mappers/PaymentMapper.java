package com.axc.solidprinciples.mappers;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.axc.solidprinciples.model.Payment;
import com.dto.PaymentResponse;
import com.dto.Paymentrequest;

@Component 
public class PaymentMapper {


    public static Payment toPayment(Paymentrequest paymentRequest) {

        Payment payment = new Payment();
        payment.setAccountNumber(paymentRequest.getAccountNumber());
        payment.setToAccountNumber(paymentRequest.getToAccountNumber());
       payment.setAmount(BigDecimal.valueOf(Double.parseDouble(paymentRequest.getAmount())));

        return payment;
    }

    public static PaymentResponse toPaymentResponse(Payment payment) {

        return new PaymentResponse(
            payment.getPaymentId(),
            payment.getAmount(),
            payment.getAccountNumber(),
            payment.getToAccountNumber(),
            payment.getPaymentType(),
            payment.getPaymentStatus(),
            payment.getCreatedAt()
        );
    }

}
