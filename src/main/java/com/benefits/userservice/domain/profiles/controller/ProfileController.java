package com.benefits.userservice.domain.profiles.controller;

import com.benefits.userservice.common.spec.Api;
import com.benefits.userservice.domain.profiles.business.UserProfileBusiness;
import com.benefits.userservice.domain.profiles.model.UserProfileRequest;
import com.benefits.userservice.domain.profiles.model.UserProfileResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


/*
GET /profiles?userId={id}
PUT /profiles/:id*/
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Tag(name = "Profiles", description = "관리자용 사용자 프로필 API 입니다. 요청 시 관리자의(supervisor) 권한(accessToken)이 필요합니다.")
public class ProfileController {

    private final UserProfileBusiness userProfileBusiness;

    @Operation(summary = "관리자의 권한으로 사용자 프로필 조회", description = "등록된 userId로 개인 사용자 프로필 정보를 조회합니다.")
    @GetMapping(path = "/profiles", params = "user_id")
    public Api<UserProfileResponse> getProfileByUserId(@Parameter(example = "1") @RequestParam(value = "user_id") Long userId){
        var response = userProfileBusiness.getProfileByUserId(userId);
        return Api.OK(response.getData());
    }

    @Operation(summary = "관리자의 권한으로 사용자 프로필 수정", description = "등록된 userId로 개인 사용자 프로필 정보를 수정합니다.")
    @PutMapping(path = "/profiles", params = "user_id")
    public Api<UserProfileResponse> updateProfile(@Parameter(example = "1") @RequestParam(value = "user_id") Long userId,
                                        @RequestBody @Valid UserProfileRequest request){
        var response = userProfileBusiness.updateProfile(userId, request);
        return Api.OK(response.getData());
    }
}
