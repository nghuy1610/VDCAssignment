package com.vdc.product.service;

import com.vdc.product.dto.request.BrandRequestDto;
import com.vdc.product.entity.Brand;
import com.vdc.product.exception.BrandException;
import com.vdc.product.mapper.Mapper;
import com.vdc.product.repository.BrandRepository;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class BrandService extends BaseService{
    BrandRepository brandRepository;

    @Autowired
    public BrandService(Mapper mapper, BrandRepository brandRepository) {
        super(mapper);
        this.brandRepository = brandRepository;
    }

    public List<Brand> createBrands(List<BrandRequestDto> dtoList) {
        List<Brand> brands = mapper.mapToList(dtoList, new TypeToken<List<Brand>>(){}.getType());
        return brandRepository.saveAll(brands);
    }

    public Brand findBrandById(String id) throws BrandException {
        return brandRepository.findById(id).orElseThrow(() -> new BrandException(BrandException.BrandError.BRAND_NOT_FOUND));
    }

    public List<Brand> findBrandByIds(Collection<String> ids) {
        return brandRepository.findAllById(ids);
    }
}
