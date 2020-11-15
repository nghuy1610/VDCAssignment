package com.vdc.audit.service;

import com.vdc.audit.dto.request.ProductPriceUpdateRequestDto;
import com.vdc.audit.dto.response.PageProductPriceUpdateResponseDto;
import com.vdc.audit.dto.response.ProductPriceUpdateResponseDto;
import com.vdc.audit.entity.ProductPriceUpdate;
import com.vdc.audit.exception.ProductException;
import com.vdc.audit.mapper.Mapper;
import com.vdc.audit.repository.ProductPriceUpdateRepository;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductPriceUpdateService extends BaseService{
    private final ProductPriceUpdateRepository productPriceUpdateRepository;

    @Autowired
    public ProductPriceUpdateService(Mapper mapper, ProductPriceUpdateRepository productPriceUpdateRepository) {
        super(mapper);
        this.productPriceUpdateRepository = productPriceUpdateRepository;
    }

    public ProductPriceUpdateResponseDto createAndRetrieveProductPriceUpdate(ProductPriceUpdateRequestDto requestDto) {
        ProductPriceUpdate productPriceUpdate = mapper.map(requestDto, ProductPriceUpdate.class);
        productPriceUpdateRepository.save(productPriceUpdate);
        return mapper.map(productPriceUpdate, ProductPriceUpdateResponseDto.class);
    }

    public ProductPriceUpdateResponseDto retrieveById(String id) throws ProductException {
        ProductPriceUpdate productPriceUpdate = productPriceUpdateRepository.findById(id)
                .orElseThrow(() -> new ProductException(ProductException.PriceUpdateError.PRICE_UPDATE_NOT_FOUND));
        return mapper.map(productPriceUpdate, ProductPriceUpdateResponseDto.class);
    }

    public PageProductPriceUpdateResponseDto retrievePageProductPriceUpdate(String productId, int page, int pageSize) {
        Page<ProductPriceUpdate> productPriceUpdates =
                productPriceUpdateRepository.findByProductId(productId, PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("createdOn"))));
        List<ProductPriceUpdateResponseDto> dtoList = mapper.mapToList(productPriceUpdates.toList(),
                new TypeToken<List<ProductPriceUpdateResponseDto>>(){}.getType());
        return new PageProductPriceUpdateResponseDto(dtoList, page, pageSize, productPriceUpdates.getTotalPages());
    }
}
