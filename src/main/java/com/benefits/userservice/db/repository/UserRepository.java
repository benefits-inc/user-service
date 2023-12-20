package com.benefits.userservice.db.repository;

import com.benefits.userservice.db.entity.users.UserEntity;
import com.benefits.userservice.db.entity.users.enums.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmailAndStatus(String email, UserStatus status);
    Optional<UserEntity> findByIdAndStatus(Long id, UserStatus status);
    Page<UserEntity> findAllByStatus(Pageable pageable, UserStatus status);
}
