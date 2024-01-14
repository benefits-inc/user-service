package com.benefits.userservice.domain._auth.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema( description = "사용자 로그인 요청을 위한 REQUEST 도메인 객체")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LoginRequest {

    @Schema( type = "string", example = "your_email@gmail.com", description = "이메일 형식")
    private String email;

    @Schema( type = "string", example = "your_password")
    private String password;
}
