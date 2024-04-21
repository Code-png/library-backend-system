package com.backend.library.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

import com.backend.library.system.filters.CsrfCookieFilter;
import com.backend.library.system.filters.JWTTokenGeneratorFilter;
import com.backend.library.system.filters.JWTTokenValidatorFilter;
import com.backend.library.system.filters.RequestValidationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");
        http.securityContext((context) -> context.requireExplicitSave(false))
        .csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler) //add .ignoringRequestMatchers for endpoints where no authentication is needed
                            .csrfTokenRepository(new CookieCsrfTokenRepository()))
        .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
        .addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class)
        .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
        .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
        .authorizeHttpRequests((request) -> request
                .requestMatchers("/login").permitAll()
                .anyRequest().authenticated())
        .formLogin(form -> form
                .loginPage("/login")
                .permitAll())
        .httpBasic(Customizer.withDefaults())
        .headers(headers -> headers
                .contentSecurityPolicy(csp -> csp
                    .policyDirectives("script-src 'self'; object-src 'none';")));
        //TODO: Add XSS protection attribute (check stackoverflow for csp deprecated)
        //TODO: Add JWT generation and validation filters
        //TODO: Add user details and user details service

        return http.build();
    }
}
