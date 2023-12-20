package com.benefits.userservice.domain.service;

import com.benefits.userservice.common.exception.ApiException;
import com.benefits.userservice.common.resultcode.ServerResultCode;
import com.benefits.userservice.common.resultcode.UserResultCode;
import com.benefits.userservice.db.entity.users.UserEntity;
import com.benefits.userservice.db.repository.UserRepository;
import com.benefits.userservice.db.entity.users.enums.UserRole;
import com.benefits.userservice.db.entity.users.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserEntity register(UserEntity userEntity){

        var saveUserEntity = Optional.ofNullable(userEntity)
                .map(ue -> {
                    ue.setRole(UserRole.USER);
                    ue.setStatus(UserStatus.REGISTERED);
                    ue.setRegisteredAt(LocalDateTime.now());
                    return ue;
                }).orElseThrow(()-> new ApiException(ServerResultCode.NULL_POINT_ERROR, "UserEntity is Null"));
        return userRepository.save(saveUserEntity);
    }

    @Transactional(readOnly = true)
    public Page<UserEntity> getAllUsers(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public UserEntity getUserByIdWithThrow(Long id){
        return userRepository.findById(id).orElseThrow(() -> new ApiException(UserResultCode.NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public UserEntity getUserByEmailWithThrow(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new ApiException(UserResultCode.NOT_FOUND));
    }


    public UserEntity updateUser(UserEntity userEntity){
        var updateUserEntity = Optional.ofNullable(userEntity)
                .orElseThrow(()-> new ApiException(ServerResultCode.NULL_POINT_ERROR, "UserEntity is Null"));
        return userRepository.save(updateUserEntity);
    }


    public void deleteUser(UserEntity userEntity){
        userRepository.save(userEntity);
    }

}
