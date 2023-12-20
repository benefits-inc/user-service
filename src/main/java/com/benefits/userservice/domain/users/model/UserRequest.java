package com.benefits.userservice.domain.users.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    // @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$", message = "비밀번호 생성 규칙이 일치하지 않습니다") // 영문 숫자 특수문자 8~16
    private String password;

    @NotBlank
    // @Pattern(regexp = "^(?:(010\\d{4})|(070\\d{4})|(01[1|6|7|8|9]\\d{3,4}))(\\d{4})$" , message = "휴대전화 형식이 올바르지 않습니다")
    private String phone;

}
