package com.benefits.userservice.db.entity.users.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatus {

    REGISTERED("등록"),
    UNREGISTERED("등록"),

    ;
    private final String description;
}
