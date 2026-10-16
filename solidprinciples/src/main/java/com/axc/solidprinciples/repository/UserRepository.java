package com.axc.solidprinciples.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.axc.solidprinciples.model.User;

import jakarta.persistence.LockModeType;

@Repository 
public interface UserRepository extends JpaRepository<User, UUID> {

    
    boolean existsByMobileNumber(String mobileNumber);
    boolean existsByUserId(String userId);

    Optional<User> findByUserId(String userId);

    
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT u FROM User u WHERE u.mobileNumber = :mobileNumber")
    Optional<User> findByMobileNumberForUpdate(@Param("mobileNumber") String mobileNumber);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT u FROM User u WHERE u.userId = :userId")
    Optional<User> findByUserIdForUpdate(@Param("userId") String userId);
    
}
