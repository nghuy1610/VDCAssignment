package com.vdc.audit.service;

import com.vdc.audit.dto.request.CustomerBehaviorRequestDto;
import com.vdc.audit.dto.response.CustomerBehaviorResponseDto;
import com.vdc.audit.dto.response.PageCustomerBehaviorResponseDto;
import com.vdc.audit.entity.CustomerBehavior;
import com.vdc.audit.exception.CustomerException;
import com.vdc.audit.mapper.Mapper;
import com.vdc.audit.repository.CustomerBehaviorRepository;
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
public class CustomerBehaviorService extends BaseService{
    private final CustomerBehaviorRepository customerBehaviorRepository;

    @Autowired
    public CustomerBehaviorService(Mapper mapper, CustomerBehaviorRepository customerBehaviorRepository) {
        super(mapper);
        this.customerBehaviorRepository = customerBehaviorRepository;
    }

    public CustomerBehaviorResponseDto createdAndRetrieveCustomerBehavior(CustomerBehaviorRequestDto requestDto) {
        CustomerBehavior customerBehavior = mapper.map(requestDto, CustomerBehavior.class);
        customerBehaviorRepository.save(customerBehavior);
        return mapper.map(customerBehavior, CustomerBehaviorResponseDto.class);
    }

    public CustomerBehaviorResponseDto retrieveById(String id) throws CustomerException {
        CustomerBehavior customerBehavior = customerBehaviorRepository.findById(id)
                .orElseThrow(() -> new CustomerException(CustomerException.CustomerBehaviorError.BEHAVIOR_NOT_FOUND));
        return mapper.map(customerBehavior, CustomerBehaviorResponseDto.class);
    }

    public PageCustomerBehaviorResponseDto retrievePageCustomerBehavior(String customerId, int page, int pageSize) {
        Page<CustomerBehavior> customerBehaviors =
                customerBehaviorRepository.findByCustomerId(customerId, PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("createdOn"))));
        List<CustomerBehaviorResponseDto> dtoList = mapper.mapToList(customerBehaviors.toList(), new TypeToken<List<CustomerBehaviorResponseDto>>(){}.getType());
        return new PageCustomerBehaviorResponseDto(dtoList, page, pageSize, customerBehaviors.getTotalPages());
    }
}
