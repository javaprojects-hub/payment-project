package com.axc.solidprinciples.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.axc.solidprinciples.constant.KycStatus;
import com.axc.solidprinciples.model.Kyc;
import com.axc.solidprinciples.model.User;
import com.axc.solidprinciples.repository.KycRepository;
import com.axc.solidprinciples.repository.UserRepository;
import com.axc.solidprinciples.service.IKycService;





@Service
public class KycServiceImpls implements IKycService {
  

  private final KycRepository kycRepository;
 


  public KycServiceImpls(UserRepository userRepository, KycRepository kycRepository) {
    
    this.kycRepository = kycRepository;
  }

    @Override
    public void createkycForUser(User user) {

     
       Kyc kyc = new Kyc();

       kyc.setKycStatus(KycStatus.PENDING);
       kyc.setUser(user);
       kyc.setCreatedAt(LocalDateTime.now());
       kyc.setUpdatedAt(LocalDateTime.now());

       kycRepository.save(kyc);

    }

}
