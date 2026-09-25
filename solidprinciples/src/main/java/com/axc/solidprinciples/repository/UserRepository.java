package com.axc.solidprinciples.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axc.solidprinciples.model.User;

@Repository 
public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByMobileNumber(String mobileNumber);
}
