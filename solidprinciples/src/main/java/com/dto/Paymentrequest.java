package com.dto;

import lombok.Data;

@Data 
public class Paymentrequest {

   private String accountNumber;

    private String toAccountNumber;

    private String amount;

}
