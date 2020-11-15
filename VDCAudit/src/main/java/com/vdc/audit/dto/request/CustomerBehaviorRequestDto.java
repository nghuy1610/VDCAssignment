package com.vdc.audit.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomerBehaviorRequestDto {
    private String customerId;
    private String behaviorType;
    private String behaviorDetail;
}
