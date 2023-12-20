package com.benefits.userservice.db.repository;

import com.benefits.userservice.db.entity.profile.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository  extends JpaRepository<UserProfileEntity, Long> {
    Optional<UserProfileEntity> findByUserId(Long userId);
    Optional<UserProfileEntity> findByIdAndUserId(Long id, Long userId);

}
