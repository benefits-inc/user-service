package com.benefits.userservice.domain.profiles.model;

import com.benefits.userservice.db.entity.profile.enums.UserProfileGrade;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Schema( description = "사용자 프로필 응답을 위한 RESPONSE 도메인 객체")
public class UserProfileResponse {

    @Schema( type = "integer", example = "1", description = "profile의 id")
    private Long id;

    private Long userId;

    @Schema( type = "string", example = "https://avatars.githubusercontent.com/u/54789601?v=4", description = "이미지 주소")
    private String profileImageUrl;

    @Schema( type = "string", example = "매운맛 카레", description = "사용자 닉네임")
    private String nickName;

    @Schema( type = "string", example = "BRONZE", description = "사용자 등급 - BRONZE(브론즈), SILVER(실버), GOLD(골드)")
    private UserProfileGrade grade;
}
