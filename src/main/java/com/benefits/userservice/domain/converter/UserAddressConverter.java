package com.benefits.userservice.domain.converter;

import com.benefits.userservice.common.annotation.Converter;
import com.benefits.userservice.db.entity.address.UserAddressEntity;
import com.benefits.userservice.domain.controller.model.response.UserAddressResponse;

import java.util.List;
import java.util.stream.Collectors;

@Converter
public class UserAddressConverter {
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
                .address1(userAddressEntity.getAddress1())
                .address2(userAddressEntity.getAddress2())
                .phone(userAddressEntity.getPhone())
                .receiveType(userAddressEntity.getReceiveType())
                .receiveMessage(userAddressEntity.getReceiveMessage())
                .build();
    }
}
