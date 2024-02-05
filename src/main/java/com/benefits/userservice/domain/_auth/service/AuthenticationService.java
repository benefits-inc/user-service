package com.benefits.userservice.domain._auth.service;

import com.benefits.userservice.common.exception.ApiException;
import com.benefits.userservice.common.resultcode.TokenResultCode;
import com.benefits.userservice.common.resultcode.UserResultCode;
import com.benefits.userservice.db.entity.users.UserEntity;
import com.benefits.userservice.domain._auth.model.TokenResponse;
import com.benefits.userservice.domain._token.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final Environment env;
    private final TokenService tokenService;
    private final RedisTemplate<String, String> redisTemplate;

    public TokenResponse login(UserEntity userEntity) {
        var accessTokenDto = tokenService.issueAccessToken(userEntity);
        var refreshTokenDto = tokenService.issueRefreshToken(userEntity);

        ResponseCookie responseCookie = ResponseCookie.from("refreshToken", refreshTokenDto.getToken())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .sameSite("None")
                .domain(env.getProperty("cookie.domain"))
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

    public void logout(HttpServletRequest request){

        // 이미 gateway-service 에서 null, invalid 검증하긴 하긴 함..
        var authorization= Optional.of(request.getHeader("Authorization"))
                .orElseThrow(() -> new ApiException(TokenResultCode.AUTHORIZATION_TOKEN_NOT_FOUND));

        var accessToken = authorization.replace("Bearer ", "");

        var acessTokenPayload = tokenService.payloadParser(accessToken);
        var accessTokenExpiredAt = new Date(Long.parseLong(acessTokenPayload.getExp()) * 1000L);

        redisTemplate.opsForValue().set(accessToken, accessToken);
        redisTemplate.expireAt(accessToken, accessTokenExpiredAt);


        var refreshTokenCookie = Arrays.stream(request.getCookies())
                .filter(it ->{
                    return it.getName().equals("refreshToken");
                }).findAny().orElseThrow(() -> new ApiException(TokenResultCode.AUTHORIZATION_TOKEN_NOT_FOUND));

        var refreshToken = refreshTokenCookie.getValue();
        var refreshTokenPayload = tokenService.payloadParser(refreshToken);
        var refreshTokenExpiredAt = new Date(Long.parseLong(refreshTokenPayload.getExp()) * 1000L);
        redisTemplate.opsForValue().set(refreshToken, refreshToken);
        redisTemplate.expireAt(refreshToken, refreshTokenExpiredAt);

    }
}
