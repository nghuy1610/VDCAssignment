package com.vdc.shop.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ProductResponseDto {
    private String id;
    private String name;
    private long price;
    private String category;
    private BrandResponseDto brand;
    private List<IntAttributeDto> intAttributes;
    private List<StringAttributeDto> stringAttributes;
    private List<DoubleAttributeDto> doubleAttributes;
}
