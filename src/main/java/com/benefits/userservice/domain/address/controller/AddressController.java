package com.benefits.userservice.domain.address.controller;

import com.benefits.userservice.common.spec.Api;
import com.benefits.userservice.domain.address.business.UserAddressBusiness;
import com.benefits.userservice.domain.address.model.UserAddressRequest;
import com.benefits.userservice.domain.address.model.UserAddressResponse;
import com.benefits.userservice.domain.users.model.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Tag(name = "Address", description = "관리자용 사용자 주소 API 입니다. 요청 시 관리자용(supervisor) 권한(accessToken)이 필요합니다.")
public class AddressController {

    private final UserAddressBusiness userAddressBusiness;

    @Operation(summary = "관리자의 권한으로 특정 사용자의 주소 등록", description = "등록된 특정 사용자의 주소를 등록합니다.")
    @PostMapping(path = "/address" , params = "user_id")
    public Api<UserAddressResponse> registerProfile(@Parameter(example = "1") @RequestParam(value = "user_id") Long userId,
                                                    @RequestBody @Valid UserAddressRequest request) {
        var response = userAddressBusiness.register(userId, request);
        return Api.CREATE(response.getData());
    }

    @Operation(summary = "관리자의 권한으로 특정 사용자의 모든 주소 조회", description = "등록된 특정 사용자의 모든 주소를 조회합니다.")
    @GetMapping(path = "/address", params = "user_id")
    public Api<List<UserAddressResponse>> getAllUserAddressByUserId(@Parameter(example = "1")  @RequestParam(value = "user_id") Long userId){
        var response = userAddressBusiness.getAllUserAddressByUserId(userId);
        return Api.OK(response.getData());
    }

    @Operation(summary = "관리자의 권한으로 사용자 특정 주소 수정", description = "등록된 사용자의 특정 주소를 수정합니다." +
            "<br><br> 주소는 user_id당 최대 3개까지 등록될 수 있기 때문에 " +
            "<br><br> 수정 시에 unique한 address의 id가 포함됩니다.")
    @PutMapping(path = "/address/{id}", params = "user_id")
    public Api<UserAddressResponse> updateUser(@Parameter(example = "1") @PathVariable(value = "id") Long id,
                                               @Parameter(example = "1") @RequestParam(value = "user_id") Long userId,
                                        @RequestBody @Valid UserAddressRequest request){
        var response = userAddressBusiness.updateAddress(id, userId, request);
        return Api.OK(response.getData());
    }


    @Operation(summary = "관리자의 권한으로 사용자의 특정 주소 삭제 - hard delete", description = "등록된 사용자의 특정 주소를 삭제합니다." +
            "<br><br> 주소는 user_id당 최대 3개까지 등록될 수 있기 때문에 " +
            "<br><br> 삭제 시에 unique한 address의 id가 포함됩니다.")
    @DeleteMapping(path = "/address/{id}", params = "user_id")
    public Api<UserResponse> deleteAddress(@Parameter(example = "1") @PathVariable(name = "id") Long id,
                                           @Parameter(example = "1") @RequestParam(value = "user_id") Long userId){
        userAddressBusiness.deleteAddress(id, userId);
        return Api.OK(null);
    }

}
