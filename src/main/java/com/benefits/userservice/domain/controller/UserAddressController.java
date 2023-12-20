package com.benefits.userservice.domain.controller;

import com.benefits.userservice.common.spec.Api;
import com.benefits.userservice.domain.business.UserAddressBusiness;
import com.benefits.userservice.domain.controller.model.request.UserAddressRequest;
import com.benefits.userservice.domain.controller.model.response.UserAddressResponse;
import com.benefits.userservice.domain.controller.model.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserAddressController {

    private final UserAddressBusiness userAddressBusiness;
    @PostMapping("/address")
    public Api<UserAddressResponse> registerProfile(@RequestBody @Valid UserAddressRequest request) {
        var response = userAddressBusiness.register(request);
        return Api.CREATE(response.getData());
    }

    @GetMapping(value = "/address", params = "userId")
    public Api<List<UserAddressResponse>> getAllUserAddressByUserId(@RequestParam(value = "userId") Long userId){
        var response = userAddressBusiness.getAllUserAddressByUserId(userId);
        return Api.OK(response.getData());
    }

    @PutMapping(value = "/address/{id}")
    public Api<UserAddressResponse> updateUser(@PathVariable(value = "id") Long id,
                                        @RequestBody @Valid UserAddressRequest request){
        var response = userAddressBusiness.updateAddress(id, request);
        return Api.OK(response.getData());
    }

    @DeleteMapping("/address/{id}")
    public Api<UserResponse> deleteAddress(@PathVariable(name = "id") Long id){
        userAddressBusiness.deleteAddress(id);
        return Api.OK(null);
    }
}
