package com.benefits.userservice.entity.address.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserReceiveType {

    DOOR("문 앞"),
    SELF("직접 받고 부재 시 문 앞"),
    OFFICE("경비실"),
    POST("우편함"),
    ETC("기타")
    ;
    private final String description;
}

