package com.benefits.userservice.domain.address.service;

import com.benefits.userservice.common.exception.ApiException;
import com.benefits.userservice.common.resultcode.ServerResultCode;
import com.benefits.userservice.common.resultcode.UserResultCode;
import com.benefits.userservice.db.entity.address.UserAddressEntity;
import com.benefits.userservice.db.repository.UserAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserAddressService {
    private final UserAddressRepository userAddressRepository;

    public UserAddressEntity userAddressRegister(UserAddressEntity userAddressEntity){
        var saveUserAddressEntity = Optional.ofNullable(userAddressEntity)
                .orElseThrow(()-> new ApiException(ServerResultCode.NULL_POINT_ERROR, "UserAddressEntity is Null"));
        return userAddressRepository.save(saveUserAddressEntity);
    }

    public List<UserAddressEntity> getAllUserAddressByUserId(Long userId){
        return userAddressRepository.findAllByUserId(userId);
    }

    public UserAddressEntity getUserAddressByIdAndUserId(Long id, Long userId){
        return userAddressRepository.findAllByIdAndUserId(id, userId)
                .orElseThrow(() -> new ApiException(UserResultCode.NOT_FOUND, "주소를 찾을 수 없습니다."));
    }

    public void deleteAddress(Long id) {
        userAddressRepository.deleteById(id);
    }
}
