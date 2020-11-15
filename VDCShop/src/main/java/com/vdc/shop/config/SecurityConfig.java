package com.vdc.shop.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vdc.shop.dto.ErrorDto;
import com.vdc.shop.security.CookieFilter;
import com.vdc.shop.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CookieFilter cookieFilter;
    private final ObjectMapper objectMapper;
    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(CookieFilter cookieFilter, ObjectMapper objectMapper, CustomUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.cookieFilter = cookieFilter;
        this.objectMapper = objectMapper;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthenticationEntryPoint unauthenticatedEntryPoint() {
        return (request, resp, authException) -> {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
            resp.setHeader("Content-Type", "application/json");
            ErrorDto errorDto = new ErrorDto("Authentication failed", "Authenticated failed");
            resp.getWriter().println(objectMapper.writeValueAsString(errorDto));
        };
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(cookieFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(unauthenticatedEntryPoint())
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/products/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .rememberMe().disable()
                .csrf().disable()
                .logout().disable();
    }
}
