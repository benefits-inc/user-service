package com.benefits.userservice.domain.profiles.model;

import com.benefits.userservice.db.entity.profile.enums.UserProfileGrade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileResponse {
    private Long id;

    private Long userId;

    private String profileImageUrl;

    private String nickName;

    private UserProfileGrade grade;
}
