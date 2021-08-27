package com.rideguide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rideguide.entity.UserPasswordResetRequest;

@Repository
public interface UserPasswordResetRequestRepository extends JpaRepository<UserPasswordResetRequest, Long> {
    UserPasswordResetRequest findByExternalId(String externalId);
}
