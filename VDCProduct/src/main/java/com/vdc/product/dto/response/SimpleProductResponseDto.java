package com.vdc.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor
@NoArgsConstructor
public class SimpleProductResponseDto {
    private String id;
    private String name;
    private long price;
    private String category;
    private BrandResponseDto brand;
}
