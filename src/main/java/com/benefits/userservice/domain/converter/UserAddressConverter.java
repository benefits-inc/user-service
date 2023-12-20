package com.benefits.userservice.domain.converter;

import com.benefits.userservice.common.annotation.Converter;
import com.benefits.userservice.db.entity.address.UserAddressEntity;
import com.benefits.userservice.db.entity.address.enums.UserReceiveType;
import com.benefits.userservice.db.entity.users.UserEntity;
import com.benefits.userservice.domain.controller.model.request.UserAddressRequest;
import com.benefits.userservice.domain.controller.model.response.UserAddressResponse;

import java.util.List;
import java.util.stream.Collectors;

@Converter
public class UserAddressConverter {

    public UserAddressEntity toEntity(UserAddressRequest userAddressRequest, UserEntity userEntity){
        return UserAddressEntity.builder()
                .receiver(userAddressRequest.getReceiver())
                .address1(userAddressRequest.getAddress1())
                .address2(userAddressRequest.getAddress2())
                .phone(userAddressRequest.getPhone())
                .receiveType(UserReceiveType.valueOf(userAddressRequest.getReceiveType()))
                .receiveMessage(userAddressRequest.getReceiveMessage())
                .user(userEntity)
                .build();
    }
    public List<UserAddressResponse> toResponseList(List<UserAddressEntity> userAddressEntityList){
        return userAddressEntityList.stream().map(userAddressEntity -> {
            return UserAddressResponse.builder()
                    .id(userAddressEntity.getId())
                    .userId(userAddressEntity.getUser().getId())
                    .address1(userAddressEntity.getAddress1())
                    .address2(userAddressEntity.getAddress2())
                    .phone(userAddressEntity.getPhone())
                    .receiveType(userAddressEntity.getReceiveType())
                    .receiveMessage(userAddressEntity.getReceiveMessage())
                    .build();
                }).collect(Collectors.toList());
    }
    public UserAddressResponse toResponse(UserAddressEntity userAddressEntity){
        return UserAddressResponse.builder()
                .id(userAddressEntity.getId())
                .userId(userAddressEntity.getUser().getId())
                .receiver(userAddressEntity.getReceiver())
                .address1(userAddressEntity.getAddress1())
                .address2(userAddressEntity.getAddress2())
                .phone(userAddressEntity.getPhone())
                .receiveType(userAddressEntity.getReceiveType())
                .receiveMessage(userAddressEntity.getReceiveMessage())
                .build();
    }
}
