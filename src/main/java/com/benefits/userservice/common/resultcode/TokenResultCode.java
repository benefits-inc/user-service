package com.benefits.userservice.common.resultcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum TokenResultCode implements ResultCodeIfs{
    // 외부, 내부, description
    INVALID_TOKEN(400, 4000, "유효하지 않은 토큰"),
    EXPIRED_TOKEN(400, 4001, "만료된 토큰"),
    TOKEN_EXCEPTION(400, 4002, "토큰 알 수 없는 에러"),
    AUTHORIZATION_TOKEN_NOT_FOUND(400, 4003, "인증 토큰 없음"),
    INVALID_PAYLOAD(HttpStatus.UNAUTHORIZED.value(), 4005, "유효하지 않은 페이로드"),
    ;

    private final Integer httpStatusCode;
    private final Integer resultCode;
    private final String message;
}
