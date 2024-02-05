package com.benefits.userservice.domain._auth.controller;


import com.benefits.userservice.domain._auth.business.AuthenticationBusiness;
import com.benefits.userservice.domain._auth.model.LoginRequest;
import com.benefits.userservice.domain._auth.model.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/open-api")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "사용자의 로그인과 로그인 연장을 담당하는 API 입니다")
public class AuthenticationController {

   private final AuthenticationBusiness authenticationBusiness;

   @Operation(summary = "사용자 로그인", description = "로그인 이용 시에 <br><br> 헤더와 응답 바디에 accessToken이 발급 됩니다. " +
                                        "<br><br> 쿠키에 HttpOnly, Secure 옵션이 추가된 보안 쿠키에 refreshToken이 발급 됩니다." +
                                        "<br><br> accessToken의 유효 시간은 1시간 이며, refreshToken의 유효 시간은 12시간 입니다.")
   @PostMapping("/login")
   public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request){
       var responseToken = authenticationBusiness.login(request);
       return ResponseEntity.status(HttpStatus.CREATED)
               .header("access_token", responseToken.getAccessToken()) //accessToken은 헤더로 내려줄지 바디로 내려줄지 선택
               .header(HttpHeaders.AUTHORIZATION, responseToken.getAccessToken()) //accessToken은 헤더로 내려줄지 바디로 내려줄지 선택
               .header(HttpHeaders.SET_COOKIE, responseToken.getResponseCookie().toString())
               .body(responseToken);
   }

    @Operation(summary = "사용자 로그인 연장", description = "보안 쿠키의 refreshToken을 이용하여 새로운 accessToken을 발급합니다.")
    @GetMapping("/restore")
    public ResponseEntity<TokenResponse> restore(HttpServletRequest request){
        var responseToken = authenticationBusiness.restore(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("access_token", responseToken.getAccessToken()) //accessToken은 헤더로 내려줄지 바디로 내려줄지 선택
                .body(responseToken);
    }


}
