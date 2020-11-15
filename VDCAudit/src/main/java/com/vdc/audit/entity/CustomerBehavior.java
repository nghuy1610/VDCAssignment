package com.vdc.audit.entity;

import com.vdc.audit.enums.CustomerBehaviorType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Setter @Getter
public class CustomerBehavior extends BaseEntity{
    private String customerId;
    @Enumerated(EnumType.STRING)
    private CustomerBehaviorType behaviorType;
    private String behaviorDetail;
}
