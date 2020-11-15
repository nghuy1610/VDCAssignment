package com.vdc.product.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter @Setter
public class StringAttribute extends BaseEntity{
    private String name;
    private String value;

    @ManyToOne
    Product product;
}
