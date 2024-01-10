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

        var gatewayIp = "http://" + env.getProperty("gateway.ip") + ":" + env.getProperty("gateway.port");
        var gatewayLocal = "http://" + env.getProperty("gateway.local") + ":" + env.getProperty("gateway.port");
        registry.addMapping("/**") 			// ex) /somePath/**, /user-service/**
                .allowedMethods("*")				// ex) .allowedMethods("GET", "POST");
                .allowCredentials(true) // front withCredential true
                //.allowedHeaders("x-requested-with, Authorization, access_token, Content-Type")
                .allowedOrigins(gatewayIp, gatewayLocal, "http://localhost:3001"); // gateway(스웨거), 프론트 허용
                // .allowedOriginPatterns("*"); 2.4부터 와일드카드는 allowedOriginPatterns 메소드로 사용 가능
    }

    @Override
    public void setEnvironment(final Environment env) {
        this.env = env;
    }
}