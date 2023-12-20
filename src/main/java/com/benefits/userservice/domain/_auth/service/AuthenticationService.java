package com.benefits.userservice.domain._auth.service;

import com.benefits.userservice.config.security.auth.model.UserSession;
import com.benefits.userservice.domain._auth.model.TokenResponse;
import com.benefits.userservice.domain._token.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final TokenService tokenService;

    public TokenResponse login(UserSession userSession) {
        var accessTokenDto = tokenService.issueAccessToken(userSession);
        var refreshTokenDto = tokenService.issueRefreshToken(userSession);

        ResponseCookie responseCookie = ResponseCookie.from("refreshToken", refreshTokenDto.getToken())
                .httpOnly(true)
                //.secure(true)
                .path("/")
                .maxAge(60 * 60 * refreshTokenDto.getMaxAge())
                //.domain("mydomain.com")
                .build();

        return TokenResponse.builder()
                .accessToken(accessTokenDto.getToken())
                .expiredAt(accessTokenDto.getExpiredAt())
                .responseCookie(responseCookie)
                .build();
    }

}