package com.axc.solidprinciples.service;

import org.springframework.web.multipart.MultipartFile;

import com.axc.solidprinciples.constant.DocumentType;

public interface iKycDocument {

    void uploadDocument(Long id , Long kycId, DocumentType documentType, MultipartFile file);
}
