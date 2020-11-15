package com.vdc.product.service;

import com.vdc.product.mapper.Mapper;

public abstract class BaseService {
    protected Mapper mapper;

    protected BaseService(Mapper mapper) {
        this.mapper = mapper;
    }
}
