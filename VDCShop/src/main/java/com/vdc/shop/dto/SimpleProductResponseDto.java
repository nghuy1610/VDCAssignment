package com.vdc.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SimpleProductResponseDto {
    private String id;
    private String name;
    private long price;
    private String category;
    private BrandResponseDto brand;
}
