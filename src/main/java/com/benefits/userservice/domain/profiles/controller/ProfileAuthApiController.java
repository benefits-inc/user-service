package com.benefits.userservice.domain.profiles.controller;

import com.benefits.userservice.aop.annotation.UserSelfRole;
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


@RestController
@RequestMapping("/auth-api")
@RequiredArgsConstructor
@Tag(name = "Profiles", description = "사용자 프로필 AUTH API 입니다. 유저의 권한(accessToken)이 필요합니다.")
public class ProfileAuthApiController {

    private final UserProfileBusiness userProfileBusiness;

    @Operation(summary = "사용자 프로필 조회", description = "등록된 userId로 개인 사용자 프로필 정보를 조회합니다. " +
            "<br><br> 개인 사용자 정보이므로 자신만 조회할 수 있습니다.")
    @UserSelfRole
    @GetMapping(path = "/profiles", params = "user_id")
    public Api<UserProfileResponse> getProfileByUserId(@Parameter(example = "1") @RequestParam(value = "user_id") Long userId){
        var response = userProfileBusiness.getProfileByUserId(userId);
        return Api.OK(response.getData());
    }

    @Operation(summary = "사용자 프로필 수정", description = "등록된 profileId로 개인 사용자 프로필 수정합니다. " +
            "<br><br> 개인 사용자 정보이므로 자신만 수정할 수 있습니다.")
    @UserSelfRole
    @PutMapping(path = "/profiles", params = "user_id")
    public Api<UserProfileResponse> updateProfile(@Parameter(example = "1") @RequestParam(value = "user_id") Long userId,
                                        @RequestBody @Valid UserProfileRequest request){
        var response = userProfileBusiness.updateProfile(userId, request);
        return Api.OK(response.getData());
    }
}
