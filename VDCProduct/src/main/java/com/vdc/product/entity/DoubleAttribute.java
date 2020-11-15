package com.vdc.product.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter @Setter
public class DoubleAttribute extends BaseEntity{
    private String name;
    private double value;

    @ManyToOne
    Product product;
}
