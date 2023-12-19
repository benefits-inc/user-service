package com.benefits.userservice.db.entity.users.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserRole {
    ADMIN("관리자"),
    USER("일반회원")
    ;

    private final String description;
}
