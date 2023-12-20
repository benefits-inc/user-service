package com.benefits.userservice.domain.users.service;

import com.benefits.userservice.common.exception.ApiException;
import com.benefits.userservice.common.resultcode.UserResultCode;
import com.benefits.userservice.db.entity.profile.UserProfileEntity;
import com.benefits.userservice.db.repository.UserProfileRepository;
import com.benefits.userservice.db.entity.profile.enums.UserProfileGrade;
import com.benefits.userservice.db.entity.users.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;

    public UserProfileEntity userProfileRegister(UserEntity userEntity){
        var userProfileEntity = UserProfileEntity.builder()
                        .user(userEntity)
                        .grade(UserProfileGrade.BRONZE)
                        .build();
        return userProfileRepository.save(userProfileEntity);
    }

    public UserProfileEntity getProfileByUserIdWithThrow(Long userId){
        return userProfileRepository.findByUserId(userId)
                .orElseThrow(()-> new ApiException(UserResultCode.NOT_FOUND, "사용자 프로필이 존재하지 않습니다"));
    }

    public UserProfileEntity getProfileByIdAndUserIdWithThrow(Long id, Long userId){
        return userProfileRepository.findByIdAndUserId(id, userId)
                .orElseThrow(()-> new ApiException(UserResultCode.NOT_FOUND, "사용자 프로필이 존재하지 않습니다"));
    }

    public UserProfileEntity updateProfile(UserProfileEntity userProfileEntity){
        var updateUserProfileEntity = Optional.ofNullable(userProfileEntity)
                .orElseThrow(()-> new ApiException(UserResultCode.NOT_FOUND, "사용자 프로필이 존재하지 않습니다"));
        return userProfileRepository.save(updateUserProfileEntity);
    }


}
