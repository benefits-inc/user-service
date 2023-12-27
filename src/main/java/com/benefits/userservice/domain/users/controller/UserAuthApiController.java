package com.benefits.userservice.domain.users.controller;

import com.benefits.userservice.aop.annotation.UserSelfRole;
import com.benefits.userservice.common.spec.Api;
import com.benefits.userservice.domain.users.business.UserBusiness;
import com.benefits.userservice.domain.users.model.UserRequest;
import com.benefits.userservice.domain.users.model.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth-api")
@RequiredArgsConstructor
public class UserAuthApiController {
    private final UserBusiness userBusiness;

    @UserSelfRole
    @GetMapping("/users/{id}")
    public Api<UserResponse> getUserByIdAndStatus(@PathVariable(name = "id") Long id){
        var response = userBusiness.getUserByIdAndStatusWithThrow(id, false);
        return Api.OK(response.getData());
    }

    @UserSelfRole
    @GetMapping(path = "/users", params = "email")
    public Api<UserResponse> getUserByEmailAndStatus(@RequestParam(value = "email") String email){
        var response = userBusiness.getUserByEmailAndStatusWithThrow(email, false);
        return Api.OK(response.getData());
    }

    @UserSelfRole
    @PutMapping("/users/{id}")
    public Api<UserResponse> updateUser(@PathVariable(name = "id") Long id,
                                        @RequestBody @Valid UserRequest request){
        var response = userBusiness.updateUser(id, request);
        return Api.OK(response.getData());
    }

    @UserSelfRole
    @DeleteMapping("/users/{id}")
    public Api<UserResponse> deleteUser(@PathVariable(name = "id") Long id){
        userBusiness.deleteUser(id);
        return Api.OK(null);
    }
}
