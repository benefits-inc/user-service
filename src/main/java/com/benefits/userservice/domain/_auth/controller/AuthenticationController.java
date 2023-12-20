package com.benefits.userservice.domain._auth.controller;


import com.benefits.userservice.common.spec.Api;
import com.benefits.userservice.config.security.auth.model.UserSession;
import com.benefits.userservice.domain._auth.model.TokenResponse;
import com.benefits.userservice.domain._auth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@AuthenticationPrincipal UserSession userSession){

        var responseToken = authenticationService.login(userSession);

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("access_token", responseToken.getAccessToken()) //accessToken은 헤더로 내려줄지 바디로 내려줄지 선택
                .header(HttpHeaders.SET_COOKIE, responseToken.getResponseCookie().toString())
                .body(responseToken);
    }
}
