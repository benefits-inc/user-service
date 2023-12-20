package com.benefits.userservice.domain.profiles.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileRequest { // 첫 등록, 업데이트 용도

    @NotBlank
    private String userId;

    private String profileImageUrl;

    @NotBlank
    private String nickName;
}
