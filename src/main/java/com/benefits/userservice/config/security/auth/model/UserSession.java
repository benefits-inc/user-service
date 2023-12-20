package com.benefits.userservice.config.security.auth.model;

import com.benefits.userservice.db.entity.users.enums.UserRole;
import com.benefits.userservice.db.entity.users.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSession implements UserDetails {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private UserRole role;
    private UserStatus status;
    private LocalDateTime registeredAt;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.toString()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.status == UserStatus.REGISTERED;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status == UserStatus.REGISTERED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.status == UserStatus.REGISTERED;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
