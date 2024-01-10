package com.benefits.userservice.domain._auth.service;

import com.benefits.userservice.config.security.auth.model.UserSession;
import com.benefits.userservice.db.entity.users.UserEntity;
import com.benefits.userservice.domain._auth.model.TokenResponse;
import com.benefits.userservice.domain._token.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final TokenService tokenService;

    public TokenResponse login(UserEntity userEntity) {
        var accessTokenDto = tokenService.issueAccessToken(userEntity);
        var refreshTokenDto = tokenService.issueRefreshToken(userEntity);

        ResponseCookie responseCookie = ResponseCookie.from("refreshToken", refreshTokenDto.getToken())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .sameSite("None")
                //.domain("localhost")
                .maxAge(60 * 60 * refreshTokenDto.getMaxAge())
                .build();


        return TokenResponse.builder()
                .accessToken(accessTokenDto.getToken())
                .expiredAt(accessTokenDto.getExpiredAt())
                .responseCookie(responseCookie)
                .build();
    }

    public String validationToken(String refreshToken) {
        return tokenService.validationToken(refreshToken);
    }
    public TokenResponse restore(UserEntity userEntity) {
        var accessTokenDto = tokenService.issueAccessToken(userEntity);
        return TokenResponse.builder()
                .accessToken(accessTokenDto.getToken())
                .expiredAt(accessTokenDto.getExpiredAt())
                .build();
    }
}
