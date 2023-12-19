package com.benefits.userservice.domain.repository;

import com.benefits.userservice.entity.users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
