package com.benefits.userservice.domain.repository;

import com.benefits.userservice.entity.profile.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository  extends JpaRepository<UserProfileEntity, Long> {

    Optional<UserProfileEntity> findByUserUuid(String userUuid);
}
