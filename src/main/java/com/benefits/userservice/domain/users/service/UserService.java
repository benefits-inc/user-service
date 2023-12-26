package com.benefits.userservice.domain.users.service;

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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserEntity register(UserEntity userEntity){

        var saveUserEntity = Optional.ofNullable(userEntity)
                .map(ue -> {
                    ue.setRole(UserRole.USER);
                    ue.setPassword(passwordEncoder.encode(ue.getPassword()));
                    ue.setStatus(UserStatus.REGISTERED);
                    ue.setRegisteredAt(LocalDateTime.now());
                    return ue;
                }).orElseThrow(()-> new ApiException(ServerResultCode.NULL_POINT_ERROR, "UserEntity is Null"));
        return userRepository.save(saveUserEntity);
    }

    @Transactional(readOnly = true)
    public Page<UserEntity> getAllUsers(Pageable pageable){
        return userRepository.findAllByStatus(pageable, UserStatus.REGISTERED);
    }

    @Transactional(readOnly = true)
    public UserEntity getUserByIdWithThrow(Long id){
        return userRepository.findByIdAndStatus(id, UserStatus.REGISTERED)
                .orElseThrow(() -> new ApiException(UserResultCode.NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public UserEntity getUserByEmailWithThrow(String email){
        return userRepository.findByEmailAndStatus(email, UserStatus.REGISTERED)
                .orElseThrow(() -> new ApiException(UserResultCode.NOT_FOUND));
    }


    public UserEntity updateUser(UserEntity userEntity){
        var updateUserEntity = Optional.ofNullable(userEntity)
                .orElseThrow(()-> new ApiException(ServerResultCode.NULL_POINT_ERROR, "UserEntity is Null"));
        return userRepository.save(updateUserEntity);
    }

    public void deleteUser(UserEntity userEntity){
        userEntity.setUnregisteredAt(LocalDateTime.now());
        userRepository.save(userEntity);
    }

}
