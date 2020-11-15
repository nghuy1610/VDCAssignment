package com.vdc.product.service;

import com.vdc.product.dto.request.ProductRequestDto;
import com.vdc.product.dto.response.PageProductResponseDto;
import com.vdc.product.dto.response.ProductResponseDto;
import com.vdc.product.dto.response.SimpleProductResponseDto;
import com.vdc.product.entity.*;
import com.vdc.product.exception.BrandException;
import com.vdc.product.exception.ProductException;
import com.vdc.product.mapper.Mapper;
import com.vdc.product.repository.ProductRepository;
import com.vdc.product.repository.specification.ProductSpecification;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService extends BaseService {
    ProductRepository productRepository;
    BrandService brandService;
    IntAttributeService intAttributeService;
    DoubleAttributeService doubleAttributeService;
    StringAttributeService stringAttributeService;
    ProductSpecification productSpecification;

    @Autowired
    public ProductService(Mapper mapper, ProductRepository productRepository, BrandService brandService,
                          IntAttributeService intAttributeService, DoubleAttributeService doubleAttributeService,
                          StringAttributeService stringAttributeService, ProductSpecification productSpecification) {
        super(mapper);
        this.productRepository = productRepository;
        this.brandService = brandService;
        this.intAttributeService = intAttributeService;
        this.doubleAttributeService = doubleAttributeService;
        this.stringAttributeService = stringAttributeService;
        this.productSpecification = productSpecification;
    }

    public ProductResponseDto retrieveProductById(String id) throws ProductException {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductException(ProductException.ProductError.PRODUCT_NOT_FOUND));
        return mapper.map(product, ProductResponseDto.class);
    }

    public void createProducts(List<ProductRequestDto> dtoList) throws BrandException {
        List<String> brandIds = dtoList.stream().map(ProductRequestDto::getBrandId).collect(Collectors.toList());
        List<Brand> brands = brandService.findBrandByIds(brandIds);
        if (brandIds.size() != brands.size()) {
            throw new BrandException(BrandException.BrandError.BRAND_NOT_FOUND);
        }
        List<Product> products = mapper.mapToList(dtoList, new TypeToken<List<Product>>() {
        }.getType());

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            product.setBrand(brands.get(i));
        }
        productRepository.saveAll(products);

        for (Product product : products) {
            for (IntAttribute intAttribute : product.getIntAttributes()) {
                intAttribute.setProduct(product);
            }
            intAttributeService.saveAll(product.getIntAttributes());
            for (DoubleAttribute doubleAttribute : product.getDoubleAttributes()) {
                doubleAttribute.setProduct(product);
            }
            doubleAttributeService.saveAll(product.getDoubleAttributes());
            for (StringAttribute stringAttribute : product.getStringAttributes()) {
                stringAttribute.setProduct(product);
            }
            stringAttributeService.saveAll(product.getStringAttributes());
        }
    }

    public PageProductResponseDto retrievePageProduct(String name, String color, String brandName, List<Long> prices,
                                                      List<String> attributes, String sortDirection, int page, int pageSize) {
        Specification<Product> specification = productSpecification.productExist();
        if (StringUtils.hasText(name)) {
            specification = specification.and(productSpecification.productHasName(name));
        }
        if (!CollectionUtils.isEmpty(prices) && prices.size() == 2) {
            specification = specification.and(productSpecification.productHasPriceIn(prices.get(0), prices.get(1)));
        }
        if (StringUtils.hasText(brandName)) {
            specification = specification.and(productSpecification.productHasBrandName(brandName));
        }
        if (StringUtils.hasText(color)) {
            specification = specification.and(productSpecification.productHasColor(color));
        }

        PageRequest pageRequest = PageRequest.of(page, pageSize);
        if (!CollectionUtils.isEmpty(attributes)) {
            String[] attributeAr = attributes.toArray(new String[0]);
            pageRequest = PageRequest.of(page, pageSize, Sort.Direction.fromString(sortDirection), attributeAr);
        }

        Page<Product> products = productRepository.findAll(specification, pageRequest);
        List<SimpleProductResponseDto> dtoList = mapper.mapToList(products.toList(), new TypeToken<List<SimpleProductResponseDto>>() {
        }.getType());
        return new PageProductResponseDto(dtoList, page, pageSize, products.getTotalPages());
    }

}
