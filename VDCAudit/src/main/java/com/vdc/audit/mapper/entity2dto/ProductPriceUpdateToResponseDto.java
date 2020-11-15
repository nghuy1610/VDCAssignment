package com.vdc.audit.mapper.entity2dto;

import com.vdc.audit.dto.response.ProductPriceUpdateResponseDto;
import com.vdc.audit.entity.ProductPriceUpdate;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;

import java.time.ZonedDateTime;

public class ProductPriceUpdateToResponseDto extends PropertyMap<ProductPriceUpdate, ProductPriceUpdateResponseDto> {
    private final Converter<ZonedDateTime, Long> zonedDateTimeToLong = mappingContext -> mappingContext.getSource().toEpochSecond();

    @Override
    protected void configure() {
        using(zonedDateTimeToLong).map(source.getCreatedOn(), destination.getCreatedOn());
    }
}
