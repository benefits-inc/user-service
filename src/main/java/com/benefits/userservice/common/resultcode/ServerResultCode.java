package com.benefits.userservice.common.resultcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ServerResultCode implements ResultCodeIfs{

    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 500, "서버에러"),
    NULL_POINT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 512, "NULL"),

    ;

    private final Integer httpStatusCode;
    private final Integer resultCode;
    private final String message;
}
