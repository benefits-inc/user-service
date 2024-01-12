package com.benefits.userservice.domain.users.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema( description = "사용자 등록 요청을 위한 REQUEST 도메인 객체")
public class UserRequest {

    @NotBlank
    @Schema( type = "string", example = "홍길동", description = "사용자 이름")
    private String name;

    @Email
    @NotBlank
    @Schema( type = "string", example = "your_email@gmail.com", description = "이메일 형식")
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$", message = "비밀번호 생성 규칙이 일치하지 않습니다") // 영문 숫자 특수문자 8~16
    @Schema( type = "string", example = "your_password", description = "영문 숫자 특수문자 8~16 자리")
    private String password;

    @NotBlank
    @Pattern(regexp = "^(?:(010\\d{4})|(070\\d{4})|(01[1|6|7|8|9]\\d{3,4}))(\\d{4})$" , message = "휴대전화 형식이 올바르지 않습니다")
    @Schema( type = "string", example = "01022223333", description = "특수문자 '-' 없이 휴대폰 번호 형식")
    private String phone;

}
