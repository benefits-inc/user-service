package com.benefits.userservice.domain._token.helper;

import com.benefits.userservice.domain._token.ifs.TokenHelperIfs;
import com.benefits.userservice.domain._token.model.TokenDto;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@Primary
@RequiredArgsConstructor
public class JwtTokenHelper implements TokenHelperIfs {

    // @Value("${token.secret.key}")
    private final Environment env;


    @Override
    public TokenDto issueAccessToken(Map<String, Object> data) {
        var secretKey = Objects.requireNonNull(env.getProperty("token.secret.key"));
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
        var secretKey = Objects.requireNonNull(env.getProperty("token.secret.key"));
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
        var secretKey = Objects.requireNonNull(env.getProperty("token.secret.key"));

        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        var parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();

        try {
            var result = parser.parseClaimsJws(token);

            return new HashMap<String, Object>(result.getBody());

        }catch (Exception e){
            // 공통 exception처리 하고  에러코드 추가 해야함 (프론트에서 상태값으로 리프레시 재시도할지 로그인 할지 결정하게 해줘야함)
            if(e instanceof SignatureException){
                // 토큰이 유효하지 않을 때
                throw new RuntimeException("유효하지 않은 토큰");
            } else if (e instanceof ExpiredJwtException) {
                // 만료된 토큰
                throw new RuntimeException("만료된 토큰입니다");
            }else {
                // 그 외 예외
                throw new RuntimeException("알수 없는 토큰에러");
            }
        }
    }
}
