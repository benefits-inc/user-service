package com.benefits.userservice.domain._auth.business;

import com.benefits.userservice.common.annotation.Business;
import com.benefits.userservice.common.exception.ApiException;
import com.benefits.userservice.common.resultcode.UserResultCode;
import com.benefits.userservice.config.security.auth.model.UserSession;
import com.benefits.userservice.domain._auth.model.LoginRequest;
import com.benefits.userservice.domain._auth.model.TokenResponse;
import com.benefits.userservice.domain._auth.service.AuthenticationService;
import com.benefits.userservice.domain.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Business
@RequiredArgsConstructor
public class AuthenticationBusiness {
    private final AuthenticationManager authenticationManager;
    
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    
    public TokenResponse login(LoginRequest request){
        var userEntity = userService.getUserByEmailWithThrow(request.getEmail());

        // 공통 spec을 위한 직접 패스워드 비교
        boolean matches = passwordEncoder.matches(request.getPassword(), userEntity.getPassword());
        if(!matches){
            throw new ApiException(UserResultCode.NOT_FOUND, "일치하는 회원 정보가 없습니다");
        }

        // 토큰 화
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(request.getEmail(), request.getPassword());

        // 한번 더 security 에게 인증 확인 및 UserSession(UserDetails) 생성
        Authentication authenticationResponse = authenticationManager.authenticate(authenticationRequest);
        UserSession userSession = (UserSession) authenticationResponse.getPrincipal();
        return authenticationService.login(userSession);
    }
}
