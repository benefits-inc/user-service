package com.benefits.userservice.config.security.auth.service;

import com.benefits.userservice.db.entity.users.UserEntity;
import com.benefits.userservice.db.entity.users.enums.UserStatus;
import com.benefits.userservice.db.repository.UserRepository;
import com.benefits.userservice.config.security.auth.model.UserSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userEntity = this.getUserByEmailWithThrow(username);

        return UserSession.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .phone(userEntity.getPhone())
                .role(userEntity.getRole())
                .status(userEntity.getStatus())
                .registeredAt(userEntity.getRegisteredAt())
                .build();
    }

    @Transactional(readOnly = true)
    public UserEntity getUserByEmailWithThrow(String email){
        return userRepository.findByEmailAndStatus(email, UserStatus.REGISTERED)
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
    }

}
