package com.vdc.product.service;

import com.vdc.product.entity.DoubleAttribute;
import com.vdc.product.mapper.Mapper;
import com.vdc.product.repository.DoubleAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class DoubleAttributeService extends BaseService{
    DoubleAttributeRepository doubleAttributeRepository;

    @Autowired
    public DoubleAttributeService(Mapper mapper, DoubleAttributeRepository doubleAttributeRepository) {
        super(mapper);
        this.doubleAttributeRepository = doubleAttributeRepository;
    }

    List<DoubleAttribute> saveAll(Collection<DoubleAttribute> doubleAttributeCollection) {
        return doubleAttributeRepository.saveAll(doubleAttributeCollection);
    }
}
