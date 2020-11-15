package com.vdc.shop.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PageProductResponseDto {
    List<SimpleProductResponseDto> products;
    private int page;
    private int pageSize;
    private int totalPage;
}
