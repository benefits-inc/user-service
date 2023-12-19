package com.benefits.userservice.db.repository;

import com.benefits.userservice.db.entity.profile.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository  extends JpaRepository<UserProfileEntity, Long> {

}
