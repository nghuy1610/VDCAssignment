package com.vdc.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor
public class ErrorDto {
    private String errCode;
    private String errMsg;
}
