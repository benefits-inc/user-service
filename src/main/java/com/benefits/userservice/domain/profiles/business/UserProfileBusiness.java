package com.benefits.userservice.domain.profiles.business;

import com.benefits.userservice.common.annotation.Business;
import com.benefits.userservice.common.spec.Api;
import com.benefits.userservice.domain.profiles.model.UserProfileRequest;
import com.benefits.userservice.domain.profiles.model.UserProfileResponse;
import com.benefits.userservice.domain.profiles.converter.UserProfileConverter;
import com.benefits.userservice.domain.profiles.service.UserProfileService;
import lombok.AllArgsConstructor;

@Business
@AllArgsConstructor
public class UserProfileBusiness {
    private final UserProfileService userProfileService;
    private final UserProfileConverter userProfileConverter;

    public Api<UserProfileResponse> getProfileByUserId(Long userId){
        var profileEntity = userProfileService.getProfileByUserIdWithThrow(userId);
        var data = userProfileConverter.toResponse(profileEntity);
        return Api.<UserProfileResponse>builder()
                .data(data)
                .build();
    }

    public Api<UserProfileResponse> updateProfile(Long id, UserProfileRequest request){

        var profileEntity = userProfileService.getProfileByIdAndUserIdWithThrow(id, Long.parseLong(request.getUserId()));

        profileEntity.setProfileImageUrl(request.getProfileImageUrl());
        profileEntity.setNickName(request.getNickName());

        var updateProfileEntity = userProfileService.updateProfile(profileEntity);

        var data = userProfileConverter.toResponse(updateProfileEntity);
        return Api.<UserProfileResponse>builder()
                .data(data)
                .build();
    }



}
