package com.benefits.userservice.db.repository;

import com.benefits.userservice.db.entity.address.UserAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserAddressRepository extends JpaRepository<UserAddressEntity, Long> {
    List<UserAddressEntity> findAllByUserId(Long userId);
    Optional<UserAddressEntity> findAllByIdAndUserId(Long id, Long userId);

    void deleteByIdAndUserId(Long id, Long userId);

}
