package com.benefits.userservice.domain.converter;

import com.benefits.userservice.common.annotation.Converter;
import com.benefits.userservice.domain.model.UserProfileResponse;
import com.benefits.userservice.domain.model.UserRequest;
import com.benefits.userservice.domain.model.UserResponse;
import com.benefits.userservice.entity.users.UserEntity;

import java.util.UUID;

@Converter
public class UserConverter {
    public UserEntity toEntity(UserRequest request){
        return UserEntity.builder()
                .userId("USER-" + UUID.randomUUID().toString())
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .phone(request.getPhone())
                .build();
    }

    public UserResponse toResponse(UserEntity userEntity, UserProfileResponse userProfileResponse){
        return UserResponse.builder()
                .id(userEntity.getId())
                .userId(userEntity.getUserId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .phone(userEntity.getPhone())
                .status(userEntity.getStatus())
                .registeredAt(userEntity.getRegisteredAt())
                .unregisteredAt(userEntity.getUnregisteredAt())
                .lastLoginAt(userEntity.getLastLoginAt())
                .userProfileResponse(userProfileResponse)
                .build();
    }
    public UserResponse toResponse(UserEntity userEntity){
        return UserResponse.builder()
                .id(userEntity.getId())
                .userId(userEntity.getUserId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .phone(userEntity.getPhone())
                .status(userEntity.getStatus())
                .registeredAt(userEntity.getRegisteredAt())
                .unregisteredAt(userEntity.getUnregisteredAt())
                .lastLoginAt(userEntity.getLastLoginAt())
                .build();
    }
}