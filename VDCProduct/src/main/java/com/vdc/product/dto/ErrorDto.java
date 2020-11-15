package com.vdc.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class ErrorDto {
    private String errCode;
    private String errMsg;
}
