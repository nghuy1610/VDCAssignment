package com.vdc.product.service;

import com.vdc.product.entity.StringAttribute;
import com.vdc.product.mapper.Mapper;
import com.vdc.product.repository.StringAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class StringAttributeService extends BaseService{
    StringAttributeRepository stringAttributeRepository;

    @Autowired
    public StringAttributeService(Mapper mapper, StringAttributeRepository stringAttributeRepository) {
        super(mapper);
        this.stringAttributeRepository = stringAttributeRepository;
    }

    public List<StringAttribute> saveAll(Collection<StringAttribute> stringAttributeCollection) {
        return stringAttributeRepository.saveAll(stringAttributeCollection);
    }
}
