package com.benefits.userservice.domain.address.model;

import com.benefits.userservice.db.entity.address.enums.UserReceiveType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema( description = "사용자 주소 등록, 수정 요청을 위한 REQUEST 도메인 객체")
public class UserAddressRequest {

    @NotBlank
    @Schema( type = "string", example = "홍길동", description = "수령인")
    private String receiver;

    @NotBlank
    @Schema( type = "string", example = "주소 1", description = "기본 주소")
    private String address1;

    @NotBlank
    @Schema( type = "string", example = "주소 2", description = "상세 주소")
    private String address2;

    @NotBlank
    @Schema( type = "string", example = "000-00", description = "우편 번호")
    private String zipcode;

    @NotBlank
    @Pattern(regexp = "^(?:(010\\d{4})|(070\\d{4})|(01[1|6|7|8|9]\\d{3,4}))(\\d{4})$" , message = "휴대전화 형식이 올바르지 않습니다")
    @Schema( type = "string", example = "01022223333", description = "특수문자 '-' 없이, 수령인 휴대폰 번호")
    private String phone;

    @NotNull
    @Schema( type = "string", example = "DOOR", description = "문앞(DOOR), 직접 받고 부재 시 문 앞(SELF), 경비실(OFFICE), 우편함(POST), 기타(ETC)")
    private UserReceiveType receiveType;

    @NotBlank(message = "배송 메시지는 필수 입니다")
    @Schema( type = "string", example = "문 앞", description = "배송 메세지")
    private String receiveMessage;
}
