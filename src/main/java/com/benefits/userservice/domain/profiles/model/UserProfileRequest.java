package com.benefits.userservice.domain.profiles.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileRequest { // 첫 등록, 업데이트 용도

    @Schema( type = "string", example = "https://avatars.githubusercontent.com/u/54789601?v=4", description = "이미지 주소")
    private String profileImageUrl;

    @NotBlank
    @Schema( type = "string", example = "매운맛 카레", description = "사용자 닉네임")
    private String nickName;
}
