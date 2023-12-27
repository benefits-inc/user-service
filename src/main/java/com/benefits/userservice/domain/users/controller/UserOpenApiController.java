package com.benefits.userservice.domain.users.controller;

import com.benefits.userservice.common.spec.Api;
import com.benefits.userservice.domain.users.business.UserBusiness;
import com.benefits.userservice.domain.users.model.UserRequest;
import com.benefits.userservice.domain.users.model.UserResponse;
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
public class UserOpenApiController {
    private final UserBusiness userBusiness;
    @PostMapping("/users")
    public Api<UserResponse> registerUser(@RequestBody @Valid UserRequest request) {
        var response = userBusiness.register(request);
        return Api.CREATE(response.getData());
    }

    @GetMapping("/users/{id}")
    public Api<UserResponse> getUserByIdAndStatus(@PathVariable(name = "id") Long id){
        var response = userBusiness.getUserByIdAndStatusWithThrow(id, true);
        return Api.OK(response.getData());
    }
}
