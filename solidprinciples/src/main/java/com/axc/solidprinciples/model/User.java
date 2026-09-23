package com.axc.solidprinciples.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table (name = "user",

    indexes =  { @Index 
        (name = "idx_user_id", columnList = "user_id"), 
        @Index (name = "idx_mobile_numer", columnList = "mobile_number")
    })
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class User {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "User ID cannot be null")
    @Column(name = "user_id", unique = true, nullable = false)
    private String userId;

    @NotNull(message = "First name cannot be null")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    private String lastName;

    @NotNull(message = "Age cannot be null")
    private short age;

    @NotNull(message = "Gender cannot be null")
    private String gender;


    @Column 
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be a 10-digit number") 
    private String mobileNumber;

    @Email(message = "Email should be valid")
    @Column(unique = true) 
    private String email;

    @CreationTimestamp 
    private LocalDateTime createdAt;

    @UpdateTimestamp 
    private LocalDateTime updatedAt;
}
