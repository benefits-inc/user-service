package com.benefits.userservice.domain.controller;

import com.benefits.userservice.common.spec.Api;
import com.benefits.userservice.domain.business.UserBusiness;
import com.benefits.userservice.domain.model.UserRequest;
import com.benefits.userservice.domain.model.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
}
