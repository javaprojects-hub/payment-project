package com.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(  String accountNumber, 
                                    String transactionReferenceId,
                                    BigDecimal amount, 
                                    String status, 
                                    LocalDateTime transactionDate) {


}
