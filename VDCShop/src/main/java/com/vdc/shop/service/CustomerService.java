package com.vdc.shop.service;

import com.vdc.shop.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Value("${customer.id}")
    private String id;
    @Value("${customer.username}")
    private String username;
    @Value("${customer.password}")
    private String password;
    @Value("#{'${customer.authorities}'.split(',')}")
    private List<String> authorities;

    public CustomerDto findByUserName(String username) {
        return new CustomerDto(id, username, password, true, authorities);
    }

    public CustomerDto findById(String id) {
        return new CustomerDto(this.id, username, password, true, authorities);
    }
}
