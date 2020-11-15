package com.vdc.audit.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductPriceUpdateResponseDto {
    private String id;
    private long createdOn;
    private String productId;
    private long currentPrice;
    private long nextPrice;
}
