package com.benefits.userservice.domain.service;

import com.benefits.userservice.common.exception.ApiException;
import com.benefits.userservice.common.resultcode.UserResultCode;
import com.benefits.userservice.entity.profile.UserProfileEntity;
import com.benefits.userservice.domain.repository.UserProfileRepository;
import com.benefits.userservice.entity.profile.enums.UserProfileGrade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;

    public UserProfileEntity userProfileRegister(String userId){
        var userProfileEntity = UserProfileEntity.builder()
                        .userId(userId)
                        .grade(UserProfileGrade.BRONZE)
                        .build();
        return userProfileRepository.save(userProfileEntity);
    }

    public UserProfileEntity getUserProfile(String userId){
        return userProfileRepository.findByUserId(userId).orElseThrow(() -> new ApiException(UserResultCode.BAD_REQUEST));
    }
}
