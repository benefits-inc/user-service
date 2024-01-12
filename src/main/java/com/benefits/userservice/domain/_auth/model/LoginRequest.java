package com.benefits.userservice.domain._auth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema( description = "사용자 로그인 요청을 위한 REQUEST 도메인 객체")
public class LoginRequest {

    @Schema( type = "string", example = "your_email@gmail.com", description = "이메일 형식")
    private String email;

    @Schema( type = "string", example = "your_password")
    private String password;
}
