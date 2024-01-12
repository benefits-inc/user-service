package com.benefits.userservice.domain.address.business;

import com.benefits.userservice.common.annotation.Business;
import com.benefits.userservice.common.exception.ApiException;
import com.benefits.userservice.common.resultcode.UserResultCode;
import com.benefits.userservice.common.spec.Api;
import com.benefits.userservice.db.entity.address.enums.UserReceiveType;
import com.benefits.userservice.domain.address.model.UserAddressRequest;
import com.benefits.userservice.domain.address.model.UserAddressResponse;
import com.benefits.userservice.domain.address.converter.UserAddressConverter;
import com.benefits.userservice.domain.address.service.UserAddressService;
import com.benefits.userservice.domain.users.service.UserService;
import lombok.AllArgsConstructor;

import java.util.List;

@Business
@AllArgsConstructor
public class UserAddressBusiness {
    private final UserService userService;
    private final UserAddressService userAddressService;
    private final UserAddressConverter userAddressConverter;
    public Api<UserAddressResponse> register(Long userId, UserAddressRequest request) {

        // 최대 3개
        var address_cnt = userAddressService.getAllUserAddressByUserId(userId).size();
        if(address_cnt > 2){
            throw new ApiException(UserResultCode.BAD_REQUEST, "주소 등록은 최대 3개 까지 가능 합니다");
        }

        var userEntity = userService.getUserByIdWithThrow(userId);

        var userAddressEntity = userAddressConverter.toEntity(request, userEntity);
        var newUserAddressEntity = userAddressService.userAddressRegister(userAddressEntity);
        var data = userAddressConverter.toResponse(newUserAddressEntity);

        return Api.<UserAddressResponse>builder()
                .data(data)
                .build();
    }

    public Api<List<UserAddressResponse>> getAllUserAddressByUserId(Long userId) {
        var userAddressEntityList = userAddressService.getAllUserAddressByUserId(userId);
        var dataList = userAddressConverter.toResponseList(userAddressEntityList);
        return Api.<List<UserAddressResponse>>builder()
                .data(dataList)
                .build();
    }

    public Api<UserAddressResponse> updateAddress(Long id, Long userId, UserAddressRequest request) {

        var userAddressEntity = userAddressService.getUserAddressByIdAndUserId(id, userId);
        userAddressEntity.setReceiver(request.getReceiver());
        userAddressEntity.setAddress1(request.getAddress1());
        userAddressEntity.setAddress2(request.getAddress2());
        userAddressEntity.setUser(userAddressEntity.getUser());
        userAddressEntity.setPhone(request.getPhone());
        userAddressEntity.setReceiveType(UserReceiveType.valueOf(request.getReceiveType()));
        userAddressEntity.setReceiveMessage(request.getReceiveMessage());

        var updateUserAddressEntity = userAddressService.userAddressRegister(userAddressEntity);
        var data = userAddressConverter.toResponse(updateUserAddressEntity);
        return Api.<UserAddressResponse>builder()
                .data(data)
                .build();
    }

    public void deleteAddress(Long id, Long userId) {
        var userAddressEntity = userAddressService.getUserAddressByIdAndUserId(id, userId);
        userAddressService.deleteAddress(userAddressEntity.getId(), userAddressEntity.getUser().getId());
    }
}
