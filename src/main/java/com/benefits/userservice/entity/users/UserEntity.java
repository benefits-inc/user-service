package com.benefits.userservice.entity.users;

import com.benefits.userservice.entity.users.enums.UserRole;
import com.benefits.userservice.entity.users.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false, unique = true)
    private String userId;

    @Column(length = 45, nullable = false)
    private String name;

    @Column(length = 80, nullable = false)
    private String email;

    @Column(length = 200, nullable = false)
    private String password;

    @Column(length = 45, nullable = false)
    private String phone;

    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
    private LocalDateTime lastLoginAt;


    /*@OneToOne(mappedBy = "user")
    @ToString.Exclude
    @JsonIgnore
    private UserProfileEntity userProfile;*/
}
