package com.axc.solidprinciples.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.axc.solidprinciples.constant.KycStatus;
import com.axc.solidprinciples.model.Kyc;
import com.axc.solidprinciples.model.User;
import com.axc.solidprinciples.service.IKycService;


import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor  
public class KycServiceImpls implements IKycService {


  private final User user;

    @Override
    public void createkycForUser() {
       
       Kyc kyc = new Kyc();
       kyc.setKycStatus(KycStatus.PENDING);
       kyc.setUser(user);
       kyc.setCreatedAt(LocalDateTime.now());
       kyc.setUpdatedAt(LocalDateTime.now());

    }

}
