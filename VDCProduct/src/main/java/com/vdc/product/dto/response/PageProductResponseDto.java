package com.vdc.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class PageProductResponseDto {
    List<SimpleProductResponseDto> products;
    private int page;
    private int pageSize;
    private int totalPage;
}
