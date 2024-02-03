package com.backend.library.system.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.backend.library.system.entities.AppUser;
import com.backend.library.system.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    public CustomAuthenticationFilter (AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        if (username == null)
            username = "";
        if (password == null)
            password = "";

        username = username.trim();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        return this.getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        AppUser authenticatedUser = (AppUser) authResult.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(Utils.getJWTSecret().getBytes());
        long accessTokenExpirationMs = System.currentTimeMillis() + Utils.getAccessTokenExpirationMs();

        String access_token = JWT.create()
                .withSubject(authenticatedUser.getUsername())
                .withExpiresAt(new Date(accessTokenExpirationMs))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);

        response.setContentType(APPLICATION_JSON_VALUE);
        Cookie accessCookie = new Cookie("access_token", access_token);
        accessCookie.setHttpOnly(true);
        accessCookie.setPath("/");
        response.addCookie(accessCookie);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.getOutputStream().print("The username and password that you entered do not match.");
    }
}
