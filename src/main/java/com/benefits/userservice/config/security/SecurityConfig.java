package com.benefits.userservice.config.security;

import com.benefits.userservice.config.security.auth.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.util.matcher.IpAddressMatcher;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.function.Supplier;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig implements EnvironmentAware {
    private Environment env;
    @Override
    public void setEnvironment(final Environment env) {
        this.env = env;
    }
//    private static final String [] WHITE_LIST = {"/actuator/**"};
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return
                http
                .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(auth -> {
                        auth
                            //.requestMatchers(WHITE_LIST).permitAll()
                            .requestMatchers("/**").access((this::hasIpAddress))
                            .anyRequest().authenticated();
                    })
                    .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    //.httpBasic(Customizer.withDefaults())
                    .build();

    }

    private AuthorizationDecision hasIpAddress(Supplier<Authentication> authentication, RequestAuthorizationContext object) {

        String ALLOWED_IP_ADDRESS = env.getProperty("gateway.ip");
        String SUBNET = env.getProperty("gateway.subnet");

        IpAddressMatcher ALLOWED_IP_ADDRESS_MATCHER = new IpAddressMatcher(ALLOWED_IP_ADDRESS + SUBNET);
        return new AuthorizationDecision(ALLOWED_IP_ADDRESS_MATCHER.matches(object.getRequest()));
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthorizationService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // hash + salt
    }
}
