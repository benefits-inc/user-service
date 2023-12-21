package com.benefits.userservice.domain._auth.controller;


import com.benefits.userservice.common.spec.Api;
import com.benefits.userservice.config.security.auth.model.UserSession;
import com.benefits.userservice.domain._auth.business.AuthenticationBusiness;
import com.benefits.userservice.domain._auth.model.LoginRequest;
import com.benefits.userservice.domain._auth.model.TokenResponse;
import com.benefits.userservice.domain._auth.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AuthenticationController {

   private final AuthenticationBusiness authenticationBusiness;
   @PostMapping("/login")
   public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request){
       var responseToken = authenticationBusiness.login(request);
       return ResponseEntity.status(HttpStatus.CREATED)
               .header("access_token", responseToken.getAccessToken()) //accessToken은 헤더로 내려줄지 바디로 내려줄지 선택
               .header(HttpHeaders.SET_COOKIE, responseToken.getResponseCookie().toString())
               .body(responseToken);
   }

    @GetMapping("/restore")
    public ResponseEntity<TokenResponse> restore(HttpServletRequest request){
        var responseToken = authenticationBusiness.restore(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("access_token", responseToken.getAccessToken()) //accessToken은 헤더로 내려줄지 바디로 내려줄지 선택
                .body(responseToken);
    }
}
