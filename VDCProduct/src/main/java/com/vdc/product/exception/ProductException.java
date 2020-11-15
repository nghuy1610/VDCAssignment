package com.vdc.product.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ProductException extends BaseException{
    @Getter @AllArgsConstructor
    public enum ProductError {
        PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "ProductNotFound", "ProductNotFound");
        private final HttpStatus status;
        private final String errCode;
        private final String errMsg;
    }

    public ProductException(ProductError productError) {
        super(productError.getStatus(), productError.getErrCode(), productError.getErrMsg());
    }


}
