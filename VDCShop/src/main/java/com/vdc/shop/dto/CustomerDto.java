package com.vdc.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter @Getter @AllArgsConstructor
public class CustomerDto {
    private String id;
    private String username;
    private String password;
    private boolean enabled;
    private List<String> authorities;
}
