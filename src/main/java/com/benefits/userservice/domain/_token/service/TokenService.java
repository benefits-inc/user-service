package com.benefits.userservice.domain._token.service;


import com.benefits.userservice.config.security.auth.model.UserSession;
import com.benefits.userservice.db.entity.users.UserEntity;
import com.benefits.userservice.domain._token.ifs.TokenHelperIfs;
import com.benefits.userservice.domain._token.model.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenHelperIfs tokenHelperIfs;

    public TokenDto issueAccessToken(UserEntity userEntity){
        var data = new HashMap<String, Object>();
        data.put("sub", userEntity.getId());
        data.put("email", userEntity.getEmail());
        return tokenHelperIfs.issueAccessToken(data);
    }

    public TokenDto issueRefreshToken(UserEntity userEntity){
        var data = new HashMap<String, Object>();
        data.put("sub", userEntity.getId());
        data.put("email", userEntity.getEmail());
        return tokenHelperIfs.issueRefreshToken(data);
    }

    public String validationToken(String token){
        var map = tokenHelperIfs.validationTokenWithThrow(token);
        var email = map.get("email");
        Optional.ofNullable(email)
                .orElseThrow(() -> new RuntimeException("Token email is null"));
        return String.valueOf(email);
    }
}
