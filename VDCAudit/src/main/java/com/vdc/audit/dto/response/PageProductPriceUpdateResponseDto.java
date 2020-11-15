package com.vdc.audit.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor
public class PageProductPriceUpdateResponseDto {
    List<ProductPriceUpdateResponseDto> productPriceUpdates;
    private int page;
    private int pageSize;
    private int totalPage;
}
