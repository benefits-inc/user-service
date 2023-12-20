package com.benefits.userservice.domain.users.business;

import com.benefits.userservice.common.annotation.Business;
import com.benefits.userservice.common.spec.Api;
import com.benefits.userservice.common.spec.Pagination;
import com.benefits.userservice.db.entity.users.enums.UserStatus;
import com.benefits.userservice.domain.address.converter.UserAddressConverter;
import com.benefits.userservice.domain.users.converter.UserConverter;
import com.benefits.userservice.domain.profiles.converter.UserProfileConverter;
import com.benefits.userservice.domain.users.model.UserRequest;
import com.benefits.userservice.domain.users.model.UserResponse;
import com.benefits.userservice.domain.address.service.UserAddressService;
import com.benefits.userservice.domain.profiles.service.UserProfileService;
import com.benefits.userservice.domain.users.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Business
@AllArgsConstructor
public class UserBusiness {

    private final UserService userService;
    private final UserProfileService userProfileService;
    private final UserConverter userConverter;
    private final UserProfileConverter userProfileConverter;
    private final UserAddressService userAddressService;
    private final UserAddressConverter userAddressConverter;


    @Transactional
    public Api<UserResponse> register(UserRequest request) {
        UserResponse data = null;

            var userEntity = userConverter.toEntity(request);
            var newUserEntity = userService.register(userEntity);

            var userProfileEntity = userProfileService.userProfileRegister(newUserEntity);
            var userProfileResponse = userProfileConverter.toResponse(userProfileEntity);

            data = userConverter.toResponse(newUserEntity, userProfileResponse);


        return Api.<UserResponse>builder()
                .data(data)
                .build();
    }

    public Api<List<UserResponse>> getAllUsers(Pageable pageable) {
        var userEntities = userService.getAllUsers(pageable);

        var dataList = userEntities.stream().map(userEntity -> {
            var userProfileResponse = userProfileConverter.toResponse(userEntity.getUserProfile());
            var userAddressResponseList = userAddressConverter.toResponseList(userEntity.getUserAddressEntityList());
            return userConverter.toResponse(userEntity, userProfileResponse, userAddressResponseList);
        }).collect(Collectors.toList());

        var pagination = Pagination.toPagination(userEntities);

        return Api.<List<UserResponse>>builder()
                .data(dataList)
                .pagination(pagination)
                .build();
    }

    public Api<UserResponse> getUserById(Long id){
        var userEntity = userService.getUserByIdWithThrow(id);

        var userProfileResponse = userProfileConverter.toResponse(userEntity.getUserProfile());
        var userAddressResponseList = userAddressConverter.toResponseList(userEntity.getUserAddressEntityList());

        var data = userConverter.toResponse(userEntity, userProfileResponse, userAddressResponseList);
        return Api.<UserResponse>builder()
                .data(data)
                .build();
    }

    public Api<UserResponse> getUserByEmail(String email){
        var userEntity = userService.getUserByEmailWithThrow(email);

        var userProfileResponse = userProfileConverter.toResponse(userEntity.getUserProfile());
        var userAddressResponseList = userAddressConverter.toResponseList(userEntity.getUserAddressEntityList());

        var data = userConverter.toResponse(userEntity, userProfileResponse, userAddressResponseList);
        return Api.<UserResponse>builder()
                .data(data)
                .build();
    }

    public Api<UserResponse> updateUser(Long id, UserRequest request){

        var userEntity = userService.getUserByIdWithThrow(id);
        userEntity.setEmail(request.getEmail());
        userEntity.setName(request.getName());
        userEntity.setPassword(request.getPassword());
        userEntity.setPhone(request.getPhone());

        var updatedUserEntity = userService.updateUser(userEntity);

        var userProfileResponse = userProfileConverter.toResponse(updatedUserEntity.getUserProfile());
        var userAddressResponseList = userAddressConverter.toResponseList(updatedUserEntity.getUserAddressEntityList());

        var data = userConverter.toResponse(updatedUserEntity, userProfileResponse, userAddressResponseList);
        return Api.<UserResponse>builder()
                .data(data)
                .build();
    }

    public void deleteUser(Long id){
        var userEntity = userService.getUserByIdWithThrow(id);
        userEntity.setStatus(UserStatus.UNREGISTERED);
        userService.deleteUser(userEntity);
    }
}
