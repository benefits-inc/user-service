package com.benefits.userservice.domain.users.controller;

import com.benefits.userservice.common.spec.Api;
import com.benefits.userservice.domain.users.business.UserBusiness;
import com.benefits.userservice.domain.users.model.UserResponse;
import com.benefits.userservice.domain.users.model.UserUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth-super")
@RequiredArgsConstructor

@Tag(name = "Users", description = "관리자용 사용자 API 입니다. 요청 시 관리자의(supervisor) 권한(accessToken)이 필요합니다.")
public class UserAuthSuperController {
    private final UserBusiness userBusiness;

    /*@Operation(summary = "관리자의 권한으로 사용자 등록")
    @PostMapping("/users")
    public Api<UserResponse> registerUser(@RequestBody @Valid UserRequest request) {
        var response = userBusiness.register(request);
        return Api.CREATE(response.getData());
    }*/

    @Operation(summary = "관리자의 권한으로 전체 사용자 조회", description = "회원 상태가 탈퇴 신청, 탈퇴 진행, 탈퇴 처리 된 회원까지 전부 조회합니다.")
    @GetMapping("/users")
    public Api<List<UserResponse>> getAllUsers(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC)
                                               @ParameterObject Pageable pageable){
        var response = userBusiness.getAllUsers(pageable);
        return Api.OK(response.getData(), response.getPagination());
    }

    @Operation(summary = "관리자의 권한으로 특정 사용자 조회", description = "관리적 측면의 회원의 상세 정보가 조회 되며 (주소, 전화번호)" +
            "<br><br> 회원 상태가 탈퇴 처리, 탈퇴 진행, 탈퇴 처리 된 회원까지 전부 조회 합니다")
    @GetMapping("/users/{id}")
    public Api<UserResponse> getUserById(@Parameter(example = "1") @PathVariable(name = "id") Long id){
        var response = userBusiness.getUserById(id);
        return Api.OK(response.getData());
    }

    /*@Operation(summary = "관리자의 권한으로 특정 사용자 조회 - 이메일")
    @GetMapping(path = "/users", params = "email")
    public Api<UserResponse> getUserByEmail(@RequestParam(value = "email") String email){
        var response = userBusiness.getUserByEmail(email);
        return Api.OK(response.getData());
    }*/

    @Operation(summary = "관리자의 권한으로 특정 사용자 수정", description = "회원 상태가 탈퇴 처리, 탈퇴 진행, 탈퇴 처리 된 회원까지 수정")
    @PutMapping("/users/{id}")
    public Api<UserResponse> updateUser(@Parameter(example = "1")
                                        @PathVariable(name = "id") Long id,
                                        @RequestBody @Valid UserUpdateRequest request){
        var response = userBusiness.updateUser(id, request);
        return Api.OK(response.getData());
    }
    @Operation(summary = "관리자의 권한으로 특정 사용자 삭제(탈퇴) - soft delete")
    @DeleteMapping("/users/{id}")
    public Api<UserResponse> deleteUser(@Parameter(example = "1")
                                        @PathVariable(name = "id") Long id){
        userBusiness.deleteUser(id);
        return Api.OK(null);
    }
}
