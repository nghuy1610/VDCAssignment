package com.vdc.audit.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductPriceUpdateRequestDto {
    private String productId;
    private long currentPrice;
    private long nextPrice;
}
