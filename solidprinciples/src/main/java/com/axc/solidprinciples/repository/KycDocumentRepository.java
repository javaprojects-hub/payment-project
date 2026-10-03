package com.axc.solidprinciples.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.axc.solidprinciples.constant.DocumentType;
import com.axc.solidprinciples.model.KycDocument;


@Repository 
public interface KycDocumentRepository extends JpaRepository<KycDocument, Long>{

    Optional<KycDocument> findByKycIdAndDocumentType(
            Long kycId,
            DocumentType documentType);
}
