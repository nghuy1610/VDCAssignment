package com.vdc.product.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter @Setter
public class Brand extends BaseEntity{
    private String name;
}
