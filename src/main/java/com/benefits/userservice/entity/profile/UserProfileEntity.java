package com.benefits.userservice.entity.profile;

import com.benefits.userservice.entity.profile.enums.UserProfileGrade;
import com.benefits.userservice.entity.users.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "profiles")
public class UserProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false, unique = true)
    private String userUuid;

    @Column(length = 200)
    private String profileImageUrl;

    @Column(length = 80)
    private String nickName;

    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserProfileGrade grade;

    @OneToOne
    @ToString.Exclude
    @JsonIgnore
    private UserEntity user; // user_id
}
