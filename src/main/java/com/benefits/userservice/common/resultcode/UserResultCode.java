package com.benefits.userservice.common.resultcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserResultCode implements ResultCodeIfs {

    OK(200,200,"성공"),
    CREATE(201,201,"성공"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), 400, "잘못 된 요청"),

    ;

    private final Integer httpStatusCode;
    private final Integer resultCode;
    private final String message;
}
