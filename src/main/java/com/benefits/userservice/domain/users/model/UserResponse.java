package com.benefits.userservice.domain.users.model;


import com.benefits.userservice.db.entity.users.enums.UserStatus;
import com.benefits.userservice.domain.address.model.UserAddressResponse;
import com.benefits.userservice.domain.profiles.model.UserProfileResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema( description = "사용자 정보 응답을 위한 RESPONSE 도메인 객체")
public class UserResponse {

    @Schema( type = "integer", example = "1", description = "user_id")
    private Long id;

    @Schema( type = "string", example = "홍길동", description = "사용자 이름")
    private String name;

    @Schema( type = "string", example = "aaa@gmail.com", description = "사용자 이메일 - open-api 요청 시 null")
    private String email;

    @Schema( type = "string", example = "01022223333", description = "사용자 휴대폰 번호 - open-api 요청 시 null")
    private String phone;

    @Schema( type = "string", example = "01022223333", description = "사용자 등록 상태( 등록(REGISTERED), 해지(UNREGISTERED)) - open-api 요청 시 null")
    private UserStatus status;

    @Schema( type = "string", description = "사용자 등록 날짜")
    private LocalDateTime registeredAt;

    @Schema( type = "string", description = "사용자 해지 날짜")
    private LocalDateTime unregisteredAt;

    @Schema( type = "string", description = "마지막 로그인 날짜")
    private LocalDateTime lastLoginAt;

    @JsonProperty(value = "profile")
    private UserProfileResponse userProfileResponse;

    @JsonProperty(value = "address_list")
    private List<UserAddressResponse> userAddressResponseList;
}
