package com.benefits.userservice.domain._token.helper;

import com.benefits.userservice.common.exception.ApiException;
import com.benefits.userservice.common.resultcode.TokenResultCode;
import com.benefits.userservice.domain._token.ifs.TokenHelperIfs;
import com.benefits.userservice.domain._token.model.TokenDto;
import com.benefits.userservice.domain._token.payload.Payload;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Component
@Primary
@RequiredArgsConstructor
public class JwtTokenHelper implements TokenHelperIfs {

    // @Value("${token.secret.key}")
    private final Environment env;


    @Override
    public TokenDto issueAccessToken(Map<String, Object> data) {
        var secretKey = Objects.requireNonNull(env.getProperty("token.secret.user.key"));
        var accessTokenPlusHour = Long.parseLong(Objects.requireNonNull(env.getProperty("token.access-token.plus-hour")));

        var accessTokenExpiredLocaldateTime = LocalDateTime.now().plusHours(accessTokenPlusHour);
        var accessTokenExpiredAt = Date.from(accessTokenExpiredLocaldateTime.atZone(ZoneId.systemDefault()).toInstant());

        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        var jwtToken = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setClaims(data)
                .setExpiration(accessTokenExpiredAt)
                .compact();

        return TokenDto.builder()
                .token(jwtToken)
                .expiredAt(accessTokenExpiredLocaldateTime)
                .maxAge(accessTokenPlusHour)
                .build();
    }

    @Override
    public TokenDto issueRefreshToken(Map<String, Object> data) {
        var secretKey = Objects.requireNonNull(env.getProperty("token.secret.user.key"));
        var refreshTokenPlusHour = Long.parseLong(Objects.requireNonNull(env.getProperty("token.refresh-token.plus-hour")));


        var refreshTokenExpiredLocaldateTime = LocalDateTime.now().plusHours(refreshTokenPlusHour);
        var refreshTokenExpiredAt = Date.from(refreshTokenExpiredLocaldateTime.atZone(ZoneId.systemDefault()).toInstant());

        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        var jwtToken = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setClaims(data)
                .setExpiration(refreshTokenExpiredAt)
                .compact();

        return TokenDto.builder()
                .token(jwtToken)
                .expiredAt(refreshTokenExpiredLocaldateTime)
                .maxAge(refreshTokenPlusHour)
                .build();
    }

    @Override
    public Map<String, Object> validationTokenWithThrow(String token) {
        var secretKey = Objects.requireNonNull(env.getProperty("token.secret.user.key"));

        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        var parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();

        try {
            var result = parser.parseClaimsJws(token);

            return new HashMap<String, Object>(result.getBody());

        }catch (Exception e){
            if(e instanceof SignatureException){
                // 토큰이 유효하지 않을 때
                throw new ApiException(TokenResultCode.INVALID_TOKEN);
            } else if (e instanceof ExpiredJwtException) {
                // 만료된 토큰
                throw new ApiException(TokenResultCode.EXPIRED_TOKEN);
            }else {
                // 그 외 예외
                throw new ApiException(TokenResultCode.TOKEN_EXCEPTION);
            }
        }
    }

    @Override
    public Payload payloadParser(String token) {
        var token_payload = token.split("\\.")[1];
        byte[] decodedPayloadByte = Base64.getDecoder().decode(token_payload);

        Payload payload = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            payload = objectMapper.readValue(decodedPayloadByte, Payload.class);
        } catch (IOException e) {
            throw new ApiException(TokenResultCode.INVALID_PAYLOAD);
        }
        return payload;
    }
}
