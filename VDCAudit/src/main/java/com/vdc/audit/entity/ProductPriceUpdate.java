package com.vdc.audit.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter @Setter
public class ProductPriceUpdate extends BaseEntity{
    private String productId;
    private long currentPrice;
    private long nextPrice;
}
