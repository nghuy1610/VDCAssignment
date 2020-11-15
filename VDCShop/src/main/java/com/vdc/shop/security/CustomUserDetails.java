package com.vdc.shop.security;

import com.vdc.shop.dto.CustomerDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    private final CustomerDto user;
    private final Collection<? extends GrantedAuthority> grantedAuthorities;

    public CustomUserDetails(CustomerDto user, Collection<? extends GrantedAuthority> grantedAuthorities) {
        this.user = user;
        this.grantedAuthorities = grantedAuthorities;
    }

    public CustomerDto getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
