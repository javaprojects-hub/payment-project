package com.axc.solidprinciples.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.axc.solidprinciples.constant.DocumentType;
import com.axc.solidprinciples.service.iKycDocument;

@RestController 
@RequestMapping ("api/kyc")
public class KycController {

    private final iKycDocument kycDocumentService;

    public KycController(iKycDocument kycDocumentService) {
        this.kycDocumentService = kycDocumentService;
    }


    @PostMapping(
    value = "/{id}/{kycId}/upload",
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE
) 
    public ResponseEntity<String> uploadDocument(@PathVariable Long id,  @PathVariable Long kycId, 
        @RequestParam("documentType") DocumentType documentType, @RequestParam("file") MultipartFile file) {

        kycDocumentService.uploadDocument(id, kycId, documentType, file);

        return ResponseEntity.ok("Document uploaded successfully");
    }

}
