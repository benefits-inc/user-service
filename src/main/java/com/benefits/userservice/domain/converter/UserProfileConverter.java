package com.benefits.userservice.domain.converter;

import com.benefits.userservice.common.annotation.Converter;
import com.benefits.userservice.domain.model.response.UserProfileResponse;
import com.benefits.userservice.entity.profile.UserProfileEntity;

@Converter
public class UserProfileConverter {
    public UserProfileResponse toResponse(UserProfileEntity userProfileEntity){
        return UserProfileResponse.builder()
                .id(userProfileEntity.getId())
                .userId(userProfileEntity.getUser().getId())
                .grade(userProfileEntity.getGrade())
                .build();
    }
}
