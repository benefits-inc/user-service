package com.benefits.userservice.domain.users.controller;

import com.benefits.userservice.common.spec.Api;
import com.benefits.userservice.domain.users.business.UserBusiness;
import com.benefits.userservice.domain.users.model.UserRequest;
import com.benefits.userservice.domain.users.model.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/open-api")
@RequiredArgsConstructor

@Tag(name = "Users", description = "사용자 OPEN API 입니다. 요청 시 추가적인 권한이 필요하지 않습니다.")
public class UserOpenApiController {
    private final UserBusiness userBusiness;
/*
    @Operation(summary = "새로운 사용자를 등록합니다.")
    @PostMapping("/users")
    public Api<UserResponse> registerUser(@RequestBody @Valid UserRequest request) {
        var response = userBusiness.register(request);
        return Api.CREATE(response.getData());
    }*/

    @Operation(summary = "사용자 조회", description = "개인 정보 노출이 가능한 사용자의 데이터만 조회합니다.")
    @GetMapping("/users/{id}")
    public Api<UserResponse> getUserByIdAndStatus(@Parameter(example = "1") @PathVariable(name = "id") Long id){
        var response = userBusiness.getUserByIdAndStatusWithThrow(id, true);
        return Api.OK(response.getData());
    }
}
