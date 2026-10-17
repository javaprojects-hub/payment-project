package com.axc.solidprinciples.repository;

import java.util.List;
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

    Optional<User> findByMobileNumber(String mobileNumber);
    
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT u FROM User u WHERE u.mobileNumber = :mobileNumber")
    Optional<User> findByMobileNumberForUpdate(@Param("mobileNumber") String mobileNumber);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT u FROM User u WHERE u.userId = :userId")
    Optional<User> findByUserIdForUpdate(@Param("userId") String userId);


    @Query(
        value = "SELECT DISTINCT u FROM User u JOIN FETCH u.accounts",
        countQuery = "SELECT COUNT(DISTINCT u) FROM User u JOIN u.accounts"
    ) 
    List<User> findAllUsersWithAccounts();
    
}
