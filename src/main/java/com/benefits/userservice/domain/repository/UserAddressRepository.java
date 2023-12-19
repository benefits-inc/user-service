package com.benefits.userservice.domain.repository;

import com.benefits.userservice.entity.address.UserAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddressEntity, Long> {
}
