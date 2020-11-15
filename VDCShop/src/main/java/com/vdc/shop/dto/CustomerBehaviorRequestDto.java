package com.vdc.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor
public class CustomerBehaviorRequestDto {
    private String customerId;
    private String behaviorType;
    private String behaviorDetail;
}
