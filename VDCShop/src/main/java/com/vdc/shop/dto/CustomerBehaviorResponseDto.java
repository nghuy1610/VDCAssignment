package com.vdc.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CustomerBehaviorResponseDto {
    private String id;
    private long createdOn;
    private String customerId;
    private String behaviorType;
    private String behaviorDetail;
}
