package com.benefits.userservice.domain.service;

import com.benefits.userservice.db.entity.profile.UserProfileEntity;
import com.benefits.userservice.db.repository.UserProfileRepository;
import com.benefits.userservice.db.entity.profile.enums.UserProfileGrade;
import com.benefits.userservice.db.entity.users.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    /*public UserProfileEntity getUserProfile(String userUuid){
        return userProfileRepository.
    }*/
}
