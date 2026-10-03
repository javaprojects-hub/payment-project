package com.axc.solidprinciples.service.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.axc.solidprinciples.constant.DocumentType;
import com.axc.solidprinciples.constant.KycStatus;
import com.axc.solidprinciples.constant.VerificationStatus;
import com.axc.solidprinciples.model.Kyc;
import com.axc.solidprinciples.model.KycDocument;
import com.axc.solidprinciples.model.User;
import com.axc.solidprinciples.repository.KycDocumentRepository;
import com.axc.solidprinciples.repository.KycRepository;
import com.axc.solidprinciples.service.FileStorageService;
import com.axc.solidprinciples.service.IKycService;
import com.axc.solidprinciples.service.iKycDocument;
import com.axc.solidprinciples.utility.UniqueIdGenerator;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;





@Service
@AllArgsConstructor 
public class KycServiceImpls implements IKycService, iKycDocument {
  

  private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5MB

  private static final Set<String> ALLOWED_FILE_TYPES = Set.of( "jpg", "jpeg", "png", "pdf");

  private static final Logger logger = LoggerFactory.getLogger(KycServiceImpls.class);

  private final KycRepository kycRepository;
 
  private final KycDocumentRepository kycDocumentRepository;

  private final FileStorageService fileStorageService;

 

    @Override
    public void createkycForUser(User user) {

     
       Kyc kyc = new Kyc();

       kyc.setKycStatus(KycStatus.PENDING);
       kyc.setUser(user);
       kyc.setCreatedAt(LocalDateTime.now());
       kyc.setUpdatedAt(LocalDateTime.now());

       kycRepository.save(kyc);

    }

  /**
   * Uploads a KYC document for a user.
   *
   * @param kycId        the ID of the KYC record
   * @param documentType the type of the document being uploaded
   * @param file         the file to be uploaded
   * @throws IOException 
   * @throws IllegalArgumentException if the file is null, empty, exceeds the maximum size, or has an invalid type
   */

  @Transactional 
  @Override
	public void uploadDocument(Long id ,Long kycId, DocumentType documentType, MultipartFile file) {

      logger.info("initiated uploadDocument for KYC ID: {}, Document Type: {}", kycId, documentType);
		
         validateFile(file);

      logger.info("file validation is processed kycId = {}" , kycId);
        
       Optional<KycDocument> existingDocument =  kycDocumentRepository.findByKycIdAndDocumentType(kycId, documentType);

       if (existingDocument.isPresent()) {

        logger.error("Document of type {} already exists for KYC ID: {}", documentType, kycId);
           throw new IllegalArgumentException("Document of type " + documentType + " already exists for KYC ID: " + kycId);
       }

       String uniqueFileName = generateUniqueFileName(file.getOriginalFilename());

       logger.info("generated unique file name = {} for kycId = {}", uniqueFileName, kycId);

        String storedFileName = fileStorageService.storeFile(file, uniqueFileName);

        KycDocument kycDocument = new KycDocument();
        kycDocument.setKycId(Long.valueOf(UniqueIdGenerator.generateUniqueId()));

        Kyc kyc = kycRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("KYC not found for ID: " + id));

        kycDocument.setKyc(kyc);
        kycDocument.setDocumentType(documentType);
        kycDocument.setFileName(storedFileName);
        kycDocument.setContentType(file.getContentType());
        try {
            kycDocument.setDocumentData(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file data", e);
        }
        kycDocument.setVerificationStatus(VerificationStatus.VERIFIED);
        kycDocument.setUploadedAt(LocalDateTime.now());
        kycDocument.setVerifiedAt(LocalDateTime.now());
        
        logger.info("saving KYC document for KYC ID: {}, Document Type: {}", kycId, documentType);
        kycDocumentRepository.save(kycDocument);
      }

    private void validateFile(MultipartFile file) {

      if (file == null || file.isEmpty()) {
          throw new IllegalArgumentException("File is null or empty");
      }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("File size exceeds the maximum limit of 5MB");
        }

        String fileName = file.getOriginalFilename();

        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("File name is null or empty");
        }

        String fileType = StringUtils.getFilenameExtension(fileName);
        if (fileType == null || fileType.isBlank()) {
            throw new IllegalArgumentException("File type is null or empty");
        }
       
        if (!ALLOWED_FILE_TYPES.contains(fileType.toLowerCase())) {

            throw new IllegalArgumentException("File type is not allowed. Allowed types are: " + ALLOWED_FILE_TYPES);
        }
        

    }


    private String generateUniqueFileName(String originalFileName) {

        String fileExtension = StringUtils.getFilenameExtension(originalFileName);
        String baseName = StringUtils.stripFilenameExtension(originalFileName);
        String timestamp = String.valueOf(System.currentTimeMillis());
        return baseName + "_" + timestamp + "." + fileExtension;
    }





	

}
