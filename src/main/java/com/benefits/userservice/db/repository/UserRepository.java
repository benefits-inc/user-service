package com.benefits.userservice.db.repository;

import com.benefits.userservice.db.entity.users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
