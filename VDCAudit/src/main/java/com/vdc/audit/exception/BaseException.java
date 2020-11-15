package com.vdc.audit.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class BaseException extends Exception{
    private String errCode;
    private HttpStatus status;

    public BaseException(String s) {
        super(s);
    }

    public BaseException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public BaseException(HttpStatus status, String errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
        this.status = status;
    }

    public BaseException(HttpStatus status, String errCode, String errMsg, Throwable throwable) {
        super(errMsg, throwable);
        this.errCode = errCode;
        this.status = status;
    }
}
