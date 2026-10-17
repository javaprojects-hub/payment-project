package com.axc.solidprinciples.service;


import com.dto.DepositRequest;
import com.dto.WithdrawRequest;

public interface AccountService {

    void deposit(DepositRequest depositRequest);

    void withdraw(WithdrawRequest withdrawRequest);
}
