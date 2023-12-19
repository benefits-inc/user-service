package com.benefits.userservice.db.repository;

import com.benefits.userservice.db.entity.address.UserAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddressEntity, Long> {
}
