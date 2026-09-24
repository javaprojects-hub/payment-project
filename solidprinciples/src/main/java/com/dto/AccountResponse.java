package com.dto;

import java.math.BigDecimal;

public record AccountResponse(
    String accountId,
    BigDecimal balance
) {

}
