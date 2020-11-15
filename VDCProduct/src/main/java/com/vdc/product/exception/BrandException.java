package com.vdc.product.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public class BrandException extends BaseException{
    @Getter @AllArgsConstructor
    public enum BrandError {
        BRAND_NOT_FOUND(HttpStatus.NOT_FOUND, "BrandNotFound", "BrandNotFound");
        private final HttpStatus status;
        private final String errCode;
        private final String errMsg;
    }

    public BrandException (BrandError brandError) {
        super(brandError.getStatus(), brandError.getErrCode(), brandError.getErrMsg());
    }
}
