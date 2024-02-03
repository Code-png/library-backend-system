package com.backend.library.system.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.backend.library.system.utils.Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class AuthenticationCheckFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/api/login"))
            filterChain.doFilter(request, response);
        else {
            try {
                Cookie[] cookies = request.getCookies();
                if (cookies == null)
                    throw new ServletException("You cannot access this resource");

                Cookie accessTokenCookie = Utils.getCookie(request, "access_token");
                if (accessTokenCookie == null)
                    throw new ServletException("You cannot access this resource");

                Utils.isExpiredToken(accessTokenCookie.getValue()); //If we reach this statement, and no exception is hit, then our checks are done
                String access_token = accessTokenCookie.getValue();

                Algorithm algorithm = Algorithm.HMAC256(Utils.getJWTSecret().getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(access_token);
                String username = decodedJWT.getSubject();
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,null,null);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                filterChain.doFilter(request, response);
            } catch (Exception exception) {
                log.error("Authentication error: " + Utils.printStackTrace(exception));
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.getOutputStream().print("An authentication error has occurred, please refer to the Support Team");
            }
        }
    }
}
