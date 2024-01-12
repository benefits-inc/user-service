package com.benefits.userservice.domain.address.model;

import com.benefits.userservice.db.entity.address.enums.UserReceiveType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Schema( description = "사용자 주소 응답을 위한 RESPONSE 도메인 객체 - open-api 요청 시 전체 null 리턴")
public class UserAddressResponse {

    @Schema( type = "integer", example = "1", description = "address의 id")
    private Long id; // 3개까지 가능하니 이 id를 갖고 수정

    @Schema( type = "integer", example = "1", description = "user의 id")
    private Long userId;

    @Schema( type = "string", example = "홍길동", description = "수령인")
    private String receiver;

    @Schema( type = "string", example = "주소 1", description = "기본 주소")
    private String address1;

    @Schema( type = "string", example = "주소 2", description = "상세 주소")
    private String address2;

    @Schema( type = "string", example = "000-00", description = "우편 번호")
    private String zipcode;

    @Schema( type = "string", example = "01022223333", description = "특수문자 '-' 없이, 수령인 휴대폰 번호")
    private String phone;

    @Schema( type = "string", example = "DOOR", description = "문앞(DOOR), 직접 받고 부재 시 문 앞(SELF), 경비실(OFFICE), 우편함(POST), 기타(ETC)")
    private UserReceiveType receiveType;

    @Schema( type = "string", example = "문 앞", description = "배송 메세지")
    private String receiveMessage;
}
