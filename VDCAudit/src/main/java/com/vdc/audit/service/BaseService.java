package com.vdc.audit.service;

import com.vdc.audit.mapper.Mapper;

public abstract class BaseService {
    protected Mapper mapper;

    protected BaseService(Mapper mapper) {
        this.mapper = mapper;
    }
}
