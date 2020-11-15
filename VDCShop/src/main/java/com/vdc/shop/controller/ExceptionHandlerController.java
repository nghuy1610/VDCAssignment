package com.vdc.shop.controller;

import com.vdc.shop.dto.ErrorDto;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlerController {
    private static final Logger LOGGER = LogManager.getLogger(ExceptionHandlerController.class);

    @Value("${exception.errcode.default}")
    private String DEFAULT_ERRCODE;
    @Value("${exception.errmsg.default}")
    private String DEFAULT_ERRMSG;

    @ExceptionHandler(Exception.class)
    public @ResponseBody ResponseEntity<?> handleBaseException(Exception exception) {
        LOGGER.error(ExceptionUtils.getStackTrace(exception));
        return new ResponseEntity<>(new ErrorDto(DEFAULT_ERRCODE, DEFAULT_ERRMSG), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
