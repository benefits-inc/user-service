package com.benefits.userservice.domain.users.controller;

import com.benefits.userservice.aop.annotation.UserSelfRole;
import com.benefits.userservice.common.spec.Api;
import com.benefits.userservice.domain.users.business.UserBusiness;
import com.benefits.userservice.domain.users.model.UserRequest;
import com.benefits.userservice.domain.users.model.UserResponse;
import com.benefits.userservice.domain.users.model.UserUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth-api")
@RequiredArgsConstructor

@Tag(name = "Users", description = "사용자 정보 AUTH API 입니다. 사용자 자신의 권한(accessToken)이 필요합니다.")
public class UserAuthApiController {
    private final UserBusiness userBusiness;

    @Operation(summary = "사용자 조회 - user_id", description = "등록된 user_id로 개인 사용자 회원 정보를 조회합니다. " +
                                    "<br><br> 개인 사용자 정보이므로 자신만 조회할 수 있습니다.")
    @UserSelfRole
    @GetMapping("/users/{id}")
    public Api<UserResponse> getUserByIdAndStatus(@Parameter(example = "1") @PathVariable(name = "id") Long id){
        var response = userBusiness.getUserByIdAndStatusWithThrow(id, false);
        return Api.OK(response.getData());
    }

    @Operation(summary = "사용자 조회 - email", description = "등록된 email로 개인 사용자 회원 정보를 조회합니다. " +
                                    "<br><br> 개인 사용자 정보이므로 자신만 조회할 수 있습니다.")
    @UserSelfRole
    @GetMapping(path = "/users", params = "email")
    public Api<UserResponse> getUserByEmailAndStatus(@Parameter(example = "aaa@gmail.com") @RequestParam(value = "email") String email){
        var response = userBusiness.getUserByEmailAndStatusWithThrow(email, false);
        return Api.OK(response.getData());
    }

    @Operation(summary = "사용자 수정", description = "개인 사용자 회원 정보를 수정합니다." +
                                   "<br><br> 개인 사용자 정보이므로 자신만 수정할 수 있습니다.")
    @UserSelfRole
    @PutMapping("/users/{id}")
    public Api<UserResponse> updateUser(@Parameter(example = "1") @PathVariable(name = "id") Long id,
                                        @RequestBody @Valid UserUpdateRequest request){
        var response = userBusiness.updateUser(id, request);
        return Api.OK(response.getData());
    }

    @Operation(summary = "사용자 삭제(탈퇴) - soft delete", description = "개인 사용자 회원 정보를 삭제합니다." +
                                    "<br><br> 개인 사용자 정보이므로 자신만 삭제할 수 있습니다.")
    @UserSelfRole
    @DeleteMapping("/users/{id}")
    public Api<UserResponse> deleteUser(@Parameter(example = "1") @PathVariable(name = "id") Long id){
        userBusiness.deleteUser(id);
        return Api.OK(null);
    }
}
