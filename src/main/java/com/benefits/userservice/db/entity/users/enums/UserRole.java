package com.benefits.userservice.db.entity.users.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserRole {
    USER("일반회원")
    ;

    private final String description;
}
