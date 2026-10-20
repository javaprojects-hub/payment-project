package com.axc.solidprinciples.model;

import com.axc.solidprinciples.constant.IdempotencyStatus;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity 
@Table (name = "idempotency_recod")
@Data 
public class IdemoptencyRecord {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;

    private String operation;

    private String idempotencyKey;

    private String requestHash;

    @Enumerated(EnumType.STRING)
    private IdempotencyStatus status;

    private 
}
