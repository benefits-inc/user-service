package com.benefits.userservice.domain.converter;

import com.benefits.userservice.common.annotation.Converter;
import com.benefits.userservice.domain.controller.model.response.UserAddressResponse;
import com.benefits.userservice.domain.controller.model.response.UserProfileResponse;
import com.benefits.userservice.domain.controller.model.request.UserRequest;
import com.benefits.userservice.domain.controller.model.response.UserResponse;
import com.benefits.userservice.db.entity.users.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

@Converter
public class UserConverter {
    public UserEntity toEntity(UserRequest request){
        return UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .phone(request.getPhone())
                .build();
    }


    // register response
    public UserResponse toResponse(UserEntity userEntity, UserProfileResponse userProfileResponse){
        return UserResponse.builder()
                .id(userEntity.getId())
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
    public UserResponse toResponse(UserEntity userEntity,
                                   UserProfileResponse userProfileResponse,
                                   List<UserAddressResponse> userAddressResponseList){
        return UserResponse.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .phone(userEntity.getPhone())
                .status(userEntity.getStatus())
                .registeredAt(userEntity.getRegisteredAt())
                .unregisteredAt(userEntity.getUnregisteredAt())
                .lastLoginAt(userEntity.getLastLoginAt())
                .userProfileResponse(userProfileResponse)
                .userAddressResponseList(userAddressResponseList)
                .build();
    }

    public UserResponse toResponse(UserEntity userEntity){
        return UserResponse.builder()
                .id(userEntity.getId())
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
