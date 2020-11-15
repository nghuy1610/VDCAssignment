package com.vdc.audit.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public class CustomerException extends BaseException{
    @Getter @AllArgsConstructor
    public enum CustomerBehaviorError {
        BEHAVIOR_NOT_FOUND(HttpStatus.NOT_FOUND, "CustomerBehaviorNotFound", "CustomerBehaviorNotFound");
        private final HttpStatus httpStatus;
        private final String errCode;
        private final String errMsg;
    }

    public CustomerException(CustomerBehaviorError behaviorError) {
        super(behaviorError.getHttpStatus(), behaviorError.getErrCode(), behaviorError.getErrMsg());
    }
}
