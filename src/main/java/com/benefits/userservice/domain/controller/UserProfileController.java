package com.benefits.userservice.domain.controller;

import com.benefits.userservice.common.spec.Api;
import com.benefits.userservice.domain.business.UserProfileBusiness;
import com.benefits.userservice.domain.controller.model.request.UserProfileRequest;
import com.benefits.userservice.domain.controller.model.request.UserRequest;
import com.benefits.userservice.domain.controller.model.response.UserProfileResponse;
import com.benefits.userservice.domain.controller.model.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


/*
GET /profiles?userId={id}
PUT /profiles/:id*/
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileBusiness userProfileBusiness;

    @GetMapping(value = "/profiles", params = "userId")
    public Api<UserProfileResponse> getProfileByUserId(@RequestParam(value = "userId") Long userId){
        var response = userProfileBusiness.getProfileByUserId(userId);
        return Api.OK(response.getData());
    }

    @PutMapping("/profiles/{id}")
    public Api<UserProfileResponse> updateProfile(@PathVariable(name = "id") Long id,
                                        @RequestBody @Valid UserProfileRequest request){
        var response = userProfileBusiness.updateProfile(id, request);
        return Api.OK(response.getData());
    }
}