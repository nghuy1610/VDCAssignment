package com.vdc.product.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component
public class Mapper {
    private ModelMapper mapper;

    public Mapper() {
        mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public <S, D> List<D> mapToList(List<S> list, Type type) {
        return mapper.map(list, type);
    }

    public <S, D> D map(S source, Class<D> destinationClass) {
        return mapper.map(source, destinationClass);
    }

    public <S, D> void map(S source, D destination) {
        mapper.map(source, destination);
    }
}
