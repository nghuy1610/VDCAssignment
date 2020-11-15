package com.vdc.audit.mapper.entity2dto;

import com.vdc.audit.dto.response.CustomerBehaviorResponseDto;
import com.vdc.audit.entity.CustomerBehavior;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;

import java.time.ZonedDateTime;

public class CustomerBehaviorToResponseDto extends PropertyMap<CustomerBehavior, CustomerBehaviorResponseDto> {
    private final Converter<ZonedDateTime, Long> zonedDateTimeToLong = mappingContext -> mappingContext.getSource().toEpochSecond();

    @Override
    protected void configure() {
        using(zonedDateTimeToLong).map(source.getCreatedOn(), destination.getCreatedOn());
    }
}
