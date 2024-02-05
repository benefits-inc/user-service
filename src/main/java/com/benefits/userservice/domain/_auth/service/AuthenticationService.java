package com.benefits.userservice.domain._auth.service;

import com.benefits.userservice.common.exception.ApiException;
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

        var authorization = request.getHeader("Authorization");

        if(authorization == null || authorization.isEmpty() || authorization.isBlank()){
            throw new ApiException(UserResultCode.BAD_REQUEST, "Not valid accessToken Header");
        }

        var accessToken = authorization.replace("Bearer ", "");

        var acessTokenPayload = tokenService.payloadParser(accessToken);
        var accessTokenExpiredAt = new Date(Long.parseLong(acessTokenPayload.getExp()) * 1000L);

        redisTemplate.opsForValue().set(accessToken, accessToken);
        redisTemplate.expireAt(accessToken, accessTokenExpiredAt);


        var cookies = Arrays.stream(request.getCookies()).filter(cookie -> {
            return cookie.getName().equals("refreshToken");
        }).toList();

        if(cookies.isEmpty()){
             throw new ApiException(UserResultCode.BAD_REQUEST, "Not valid refreshToken Cookie");
        }
        var refreshToken = cookies.get(0).getValue();
        var refreshTokenPayload = tokenService.payloadParser(refreshToken);
        var refreshTokenExpiredAt = new Date(Long.parseLong(refreshTokenPayload.getExp()) * 1000L);
        redisTemplate.opsForValue().set(refreshToken, refreshToken);
        redisTemplate.expireAt(refreshToken, refreshTokenExpiredAt);

    }
}
