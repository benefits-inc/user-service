package com.benefits.userservice.config.swagger;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@Configuration
public class SwaggerConfig implements EnvironmentAware {
    @Bean
    public ModelResolver modelResolver(ObjectMapper objectMapper){
        return new ModelResolver(objectMapper);
    }
    private Environment env;
    @Bean
    public OpenAPI openAPI(){
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER).name("Authorization");
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");

        var server = new Server();
        var gatewayDomain = env.getProperty("gateway.domain");
        server.setUrl(gatewayDomain + "/user-service");
        OpenAPI openAPI = new OpenAPI();
        openAPI.setServers(Arrays.asList(server));
        return openAPI
                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
                .security(Arrays.asList(securityRequirement))
                .info(new Info()
                .title("benefits project - USER-SERVICE API")
                .description("사용자 회원가입, 로그인 및 프로필, 주소 등록하는 기능을 제공하는 API 입니다.")
                .version("1.0.0"));
    }

    @Override
    public void setEnvironment(final Environment env) {
        this.env = env;
    }
}

