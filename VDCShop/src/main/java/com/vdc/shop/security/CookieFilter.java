package com.vdc.shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CookieFilter extends OncePerRequestFilter {
    private static final String COOKIE_NAME = "vdc_id";

    CustomUserDetailsService customUserDetailsService;

    @Autowired
    public CookieFilter(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String cookie = extractCookie(httpServletRequest);
        if (StringUtils.hasText(cookie)) {
            CustomUserDetails customUserDetails = (CustomUserDetails)customUserDetailsService.loadUserById("");
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(customUserDetails, customUserDetails.getPassword(), customUserDetails.getAuthorities());
            SecurityContextHolder.clearContext();
            SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String extractCookie(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(COOKIE_NAME)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
