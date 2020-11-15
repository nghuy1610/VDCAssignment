package com.vdc.product.dto.request;

import com.vdc.product.dto.DoubleAttributeDto;
import com.vdc.product.dto.IntAttributeDto;
import com.vdc.product.dto.StringAttributeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter @Getter @AllArgsConstructor
public class ProductRequestDto {
    public ProductRequestDto() {
        this.intAttributes = new ArrayList<>();
        this.doubleAttributes = new ArrayList<>();
        this.stringAttributes = new ArrayList<>();
    }
    private String name;
    private long price;
    private String category;
    private String brandId;
    private List<IntAttributeDto> intAttributes;
    private List<StringAttributeDto> stringAttributes;
    private List<DoubleAttributeDto> doubleAttributes;
}
