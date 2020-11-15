package com.vdc.product.service;

import com.vdc.product.entity.IntAttribute;
import com.vdc.product.mapper.Mapper;
import com.vdc.product.repository.IntAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class IntAttributeService extends BaseService{
    IntAttributeRepository intAttributeRepository;

    @Autowired
    public IntAttributeService(Mapper mapper, IntAttributeRepository intAttributeRepository) {
        super(mapper);
        this.intAttributeRepository = intAttributeRepository;
    }

    public List<IntAttribute> saveAll(Collection<IntAttribute> intAttributeCollection) {
        return intAttributeRepository.saveAll(intAttributeCollection);
    }
}
