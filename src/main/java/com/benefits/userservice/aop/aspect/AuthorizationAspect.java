package com.benefits.userservice.aop.aspect;

import com.benefits.userservice.common.exception.ApiException;
import com.benefits.userservice.common.resultcode.TokenResultCode;
import com.benefits.userservice.common.resultcode.UserResultCode;
import com.benefits.userservice.domain._token.payload.Payload;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.util.Base64;

@Slf4j
@Aspect
@Component
public class AuthorizationAspect {
    //    @Pointcut(value = "within(com.benefits.userservice.domain.products.controller.ProductController)")
  /*  @Pointcut(value = "@annotation(com.benefits.userservice.aop.annotation.UserSelfRole) && args(role, ..)")
    public void roleSupervisor(){}
    @Before(value = "roleSupervisor()")
*/

    @Before(value = "@annotation(com.benefits.userservice.aop.annotation.UserSelfRole)")
    public void userAuthSelfCheck(JoinPoint joinPoint) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();


        var authorizationHeader = request.getHeader("AUTHORIZATION");
        var tokenPayload = authorizationHeader.replace("Bearer", "").split("\\.")[1];

        byte[] decodedPayloadByte = Base64.getDecoder().decode(tokenPayload);

        Payload payload = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            payload = objectMapper.readValue(decodedPayloadByte, Payload.class);

            if(request.getRequestURI().startsWith("/auth-api") && payload.getRole().equals("USER")){
                if(request.getParameter("email") != null){
                    var email = request.getParameter("email");
                    if (!email.equals(payload.getEmail())){
                        throw new ApiException(UserResultCode.NOT_ACCEPTABLE);
                    }
                }else{
                    var id = request.getRequestURI().split("\\/")[request.getRequestURI().split("\\/").length - 1];
                    if (!id.equals(payload.getSub())){
                        throw new ApiException(UserResultCode.NOT_ACCEPTABLE);
                    }
                }
            }
        } catch (IOException e) {
            throw new ApiException(TokenResultCode.INVALID_TOKEN);
        }

    }
}
