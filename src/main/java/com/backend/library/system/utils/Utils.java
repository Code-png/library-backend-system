package com.backend.library.system.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

@Log4j2
public class Utils {
    public static String printStackTrace (Exception e){
        StringWriter sw = new StringWriter();
        PrintWriter pw= new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
    public static String getJWTSecret(){
        //In production environments, we set this variable in the properties, and retrieve it using @Value
        return "b6b9f6ba347aa5e1a4d264794761e88cf8875d8cf77ec7d97d73dde7cd179771813c9325f778ad01974328a172ed1d7042fbe7f213421fbf453402ae7658e0779bb4b7b26e118ac5c83d6762f4ac1b257694d5fcba936e37c63fbd66e976727a85aed381959480e77cc5b8bf0556fa0fa090ff949a8d80f0a0f667ba2a7c5f2a619aea96781fa10619260621d70e5f47dcc8c4e97f7cfe2f436d2ed26d57738a49e19119f11be6bd0c768da5bfee35e483a81be177da2a1cb93d088427e37775acbfe536b67cf8ff4e96c522c5a00d04a8a63fc8201cacc6aa3e98a83f3b117b0c85defdb969e653f5244488fee07e560dfc2f69aa83ac7c9baf88f6df9f712a";
    }
    public static long getAccessTokenExpirationMs(){
        return 86400000L; //In production environments, we set this variable in the properties, and retrieve it using @Value
    }
    public static Cookie getCookie(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(name.equals(cookie.getName()))
                    return cookie;
            }
        }
        return null;
    }

    public static boolean isExpiredToken(String token) {
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(getJWTSecret().getBytes())
                .build()
                .parseClaimsJws(token);
        return false; //if the token is expired, the above statement would throw an ExpiredJWTException
    }
}
