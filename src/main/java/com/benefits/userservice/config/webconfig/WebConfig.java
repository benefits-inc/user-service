package com.benefits.userservice.config.webconfig;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer, EnvironmentAware {
    private Environment env;
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        // 보안쿠키는 https, localhost 가 아니면 동작 안하므로 local 환경에서는 ip가 아닌 localhost 사용 권장 - 그러므로 ip 제외
        var gatewayDomain = env.getProperty("gateway.domain"); // http://localhost:8000 또는 실제 도메인
        var frontDomain = env.getProperty("front.domain"); // http://localhost:3001 또는 실제 도메인

        registry.addMapping("/**") 			// ex) /somePath/**, /user-service/**
                .allowedMethods("*")				// ex) .allowedMethods("GET", "POST");
                .allowCredentials(true) // front withCredential true
                .exposedHeaders("Authorization", "access_token") // front axios res.headers.authorization
                .allowedOrigins(gatewayDomain, frontDomain); // gateway(스웨거), 프론트 허용
                // .allowedOriginPatterns("*"); 2.4부터 와일드카드는 allowedOriginPatterns 메소드로 사용 가능
                //.allowedHeaders("x-requested-with", "Authorization", "access_token", "Content-Type")
    }

    @Override
    public void setEnvironment(final Environment env) {
        this.env = env;
    }
}