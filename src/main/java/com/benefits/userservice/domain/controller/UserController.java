package com.benefits.userservice.domain.controller;

import com.benefits.userservice.common.spec.Api;
import com.benefits.userservice.domain.business.UserBusiness;
import com.benefits.userservice.domain.controller.model.request.UserRequest;
import com.benefits.userservice.domain.controller.model.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {
    private final UserBusiness userBusiness;
    @PostMapping("/users")
    public Api<UserResponse> registerUser(@RequestBody @Valid UserRequest request) {
        var response = userBusiness.register(request);
        return Api.CREATE(response.getData());
    }

    @GetMapping("/users")
    public Api<List<UserResponse>> getAllUsers(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC)
                                                 Pageable pageable){
        var response = userBusiness.getAllUsers(pageable);
        return Api.OK(response.getData(), response.getPagination());
    }

    @GetMapping("/users/{id}")
    public Api<UserResponse> getUserById(@PathVariable(name = "id") Long id){
        var response = userBusiness.getUserById(id);
        return Api.OK(response.getData());
    }

    @GetMapping(value = "/users", params = "email")
    public Api<UserResponse> getUserByEmail(@RequestParam(value = "email") String email){
        var response = userBusiness.getUserByEmail(email);
        return Api.OK(response.getData());
    }

    @PutMapping("/users/{id}")
    public Api<UserResponse> updateUser(@PathVariable(name = "id") Long id,
                                        @RequestBody @Valid UserRequest request){
        var response = userBusiness.updateUser(id, request);
        return Api.OK(response.getData());
    }

    @DeleteMapping("/users/{id}")
    public Api<UserResponse> deleteUser(@PathVariable(name = "id") Long id){
        userBusiness.deleteUser(id);
        return Api.OK(null);
    }
}
