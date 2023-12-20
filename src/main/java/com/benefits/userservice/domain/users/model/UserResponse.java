package com.benefits.userservice.domain.users.model;


import com.benefits.userservice.db.entity.users.enums.UserStatus;
import com.benefits.userservice.domain.address.model.UserAddressResponse;
import com.benefits.userservice.domain.profiles.model.UserProfileResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class UserResponse {

    private Long id;
    private String userId;
    private String name;
    private String email;
    private String phone;
    private UserStatus status;
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
    private LocalDateTime lastLoginAt;

    @JsonProperty(value = "profile")
    private UserProfileResponse userProfileResponse;

    @JsonProperty(value = "address_list")
    private List<UserAddressResponse> userAddressResponseList;
}
