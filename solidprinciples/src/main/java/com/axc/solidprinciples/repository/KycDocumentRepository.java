package com.axc.solidprinciples.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axc.solidprinciples.model.KycDocument;


@Repository 
public interface KycDocumentRepository extends JpaRepository<KycDocument, Long>{

}
