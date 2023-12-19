package com.benefits.userservice.domain.controller.model.response;


import com.benefits.userservice.db.entity.users.enums.UserStatus;
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

    private UserProfileResponse userProfileResponse;
    private List<UserAddressResponse> userAddressResponseList;
}
