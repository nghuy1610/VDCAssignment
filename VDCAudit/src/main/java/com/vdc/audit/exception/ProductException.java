package com.vdc.audit.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ProductException extends BaseException{

    @Getter @AllArgsConstructor
    public enum PriceUpdateError {
        PRICE_UPDATE_NOT_FOUND(HttpStatus.NOT_FOUND, "ProductPriceNotFound", "ProductPriceNotFound");
        private final HttpStatus httpStatus;
        private final String errCode;
        private final String errMsg;
    }

    public ProductException(PriceUpdateError priceUpdateError) {
        super(priceUpdateError.getHttpStatus(), priceUpdateError.getErrCode(), priceUpdateError.getErrMsg());
    }
}
