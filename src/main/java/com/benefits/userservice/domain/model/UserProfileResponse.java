package com.benefits.userservice.domain.model;

import com.benefits.userservice.entity.profile.enums.UserProfileGrade;
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

    private String userUuid;

    private String profileImageUrl;

    private String nickName;

    private UserProfileGrade grade;
}
