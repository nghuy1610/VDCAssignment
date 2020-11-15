package com.vdc.audit.mapper;

import com.vdc.audit.mapper.entity2dto.CustomerBehaviorToResponseDto;
import com.vdc.audit.mapper.entity2dto.ProductPriceUpdateToResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component
public class Mapper {
    private final ModelMapper mapper;

    public Mapper() {
        mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // ProductPrice
        mapper.addMappings(new ProductPriceUpdateToResponseDto());

        // CustomerBehavior
        mapper.addMappings(new CustomerBehaviorToResponseDto());

    }

    public <S, D> List<D> mapToList(List<S> list, Type type) {
        return mapper.map(list, type);
    }

    public <S, D> D map(S source, Class<D> destinationClass) {
        return mapper.map(source, destinationClass);
    }

}
