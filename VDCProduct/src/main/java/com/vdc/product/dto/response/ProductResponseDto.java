package com.vdc.product.dto.response;

import com.vdc.product.dto.DoubleAttributeDto;
import com.vdc.product.dto.IntAttributeDto;
import com.vdc.product.dto.StringAttributeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter @Getter @AllArgsConstructor

@NoArgsConstructor
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
