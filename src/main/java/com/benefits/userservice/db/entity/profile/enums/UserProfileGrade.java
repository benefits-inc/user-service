package com.benefits.userservice.db.entity.profile.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserProfileGrade {

    BRONZE("브론즈"),
    SILVER("실버"),
    GOLD("골드"),
    ;

    private final String description;
}
