package com.benefits.userservice.config.swagger;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Arrays;


@OpenAPIDefinition(
//        info = @Info(title = "USER-SERVICE API",
//                description = "사용자 회원가입, 로그인 및 프로필, 주소 등록하는 기능을 제공하는 API 입니다. <br><br>"
//                + "모든 요청은 Gateway를 거치며 CORS 정책에 요청 허용된 도메인은 Gateway 도메인 또는 특정 FrontEnd 도메인 입니다. <br><br>"
//                + "공개(open) API, 유저 권한(auth) API, 관리자 권한(Supervisor) API로 구성 됩니다."
//                + "http",
//
//                version = "1.0.0")
//        tags = {
//                @Tag(name = "Users Open", description = "유저 API 입니다. 요청 시 추가적인 권한이 필요하지 않습니다."),
//                @Tag(name = "Users Auth - 유저 권한 필요", description = "개인 유저 API 입니다. 요청 시 자신의 권한(토큰)이 필요합니다."),
//                @Tag(name = "Users For Supervisor", description = "관리자용(Supervisor) API 입니다. 요청 시 관리자의 권한(토큰)이 필요합니다.")
//        }
)

@Configuration
public class SwaggerConfig implements EnvironmentAware {
    private Environment env;
    @Bean
    GroupedOpenApi usersOpenApi(){
        String[] path = {"/open-api/**", "/login", "/restore"};

        return GroupedOpenApi.builder()
                .group("1. USER-SERVICE - OPEN API")
                .pathsToMatch(path)
                .build();
    }

    @Bean
    GroupedOpenApi usersAuthApi(){
        String[] path = {"/auth-user/**"};

        return GroupedOpenApi.builder()
                .group("2. USER-SERVICE - AUTH API (SELLER)")
                .pathsToMatch(path)
                .build();
    }

    @Bean
    GroupedOpenApi usersSuperApi(){
        String[] path = {"/auth-super/**"};

        return GroupedOpenApi.builder()
                .group("3. USER-SERVICE - AUTH API (SUPERVISOR)")
                .pathsToMatch(path)
                .build();
    }
    @Bean
    public ModelResolver modelResolver(ObjectMapper objectMapper){
        return new ModelResolver(objectMapper);
    }

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
                .title("USER-SERVICE API")
                .description(
                    "사용자 회원가입, 로그인 및 프로필, 주소 등록하는 기능을 제공하는 API 입니다. <br><br>"
                    + "모든 요청은 Gateway를 거치며 CORS 정책에 요청 허용된 도메인은 Gateway 도메인 또는 특정 FrontEnd 도메인 입니다. <br><br>"
                    + "공개(open) API, 유저(User) 권한 API, 관리자(Supervisor) 권한 API로 구성 됩니다.<br><br>"
                    + "관리자 권한 서비스(SUPERVISOR-SERVICE): <a href='" + gatewayDomain + "/supervisor-service/swagger-ui/index.html'>관리자 서비스</a>")
                .version("1.0.0"));
    }
    @Override
    public void setEnvironment(final Environment env) {
        this.env = env;
    }
}

