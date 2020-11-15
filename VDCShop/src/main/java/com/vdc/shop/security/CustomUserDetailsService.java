package com.vdc.shop.security;

import com.vdc.shop.dto.CustomerDto;
import com.vdc.shop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    CustomerService customerService;

    @Autowired
    public CustomUserDetailsService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomerDto customerDto = customerService.findByUserName(username);
        return buildUserDetails(customerDto);
    }

    private CustomUserDetails buildUserDetails(CustomerDto user) {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for (String authority : user.getAuthorities()) {
            authorityList.add(new SimpleGrantedAuthority(authority));
        }
        return new CustomUserDetails(user, authorityList);
    }

    public UserDetails loadUserById(String id) {
        CustomerDto user = customerService.findById(id);
        return buildUserDetails(user);
    }
}
