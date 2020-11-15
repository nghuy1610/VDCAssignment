package com.vdc.shop.service;

import com.vdc.shop.dto.CustomerBehaviorRequestDto;
import com.vdc.shop.dto.CustomerBehaviorResponseDto;
import com.vdc.shop.enums.CustomerBehaviorType;
import com.vdc.shop.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RecordService {
    private final RestTemplate restTemplate;
    @Value(("${service.audit.customer.behavior.url}"))
    private String customerBehaviorUrl;

    @Autowired
    public RecordService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
    public void recordCustomerSearchBehavior(String name, String color, String brandName, List<Long> prices, List<String> sortBy,
                                             String sortDirection) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            CustomUserDetails customUserDetails = (CustomUserDetails)authentication.getPrincipal();

            StringBuilder strBuilder = new StringBuilder();
            if (StringUtils.hasText(name)) {
                strBuilder.append(",name=").append(name);
            }
            if (StringUtils.hasText(color)) {
                strBuilder.append(",color=").append(color);
            }
            if (StringUtils.hasText(brandName)) {
                strBuilder.append(",brandName").append(brandName);
            }
            if (!CollectionUtils.isEmpty(prices) && prices.size() == 2) {
                strBuilder.append(",prices=").append(prices.get(0)).append(',').append(prices.get(1));
            }
            if (!CollectionUtils.isEmpty(sortBy) && StringUtils.hasText(sortDirection)) {
                strBuilder.append(",sortBy=").append(String.join(",", sortBy))
                        .append(",sortDirection=").append(sortDirection);
            }

            CustomerBehaviorRequestDto requestDto = new CustomerBehaviorRequestDto(customUserDetails.getUser().getId(), CustomerBehaviorType.SEARCH.name(), strBuilder.toString());

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            HttpEntity<?> entity = new HttpEntity<>(requestDto, headers);
            restTemplate.exchange(customerBehaviorUrl, HttpMethod.POST, entity, CustomerBehaviorResponseDto.class);
        }
    }

    @Async
    public void recordCustomerViewBehavior(String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            CustomUserDetails customUserDetails = (CustomUserDetails)authentication.getPrincipal();

            CustomerBehaviorRequestDto requestDto = new CustomerBehaviorRequestDto(customUserDetails.getUser().getId(), CustomerBehaviorType.VIEW.name(), id);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            HttpEntity<?> entity = new HttpEntity<>(requestDto, headers);
            restTemplate.exchange(customerBehaviorUrl, HttpMethod.POST, entity, CustomerBehaviorResponseDto.class);
        }
    }
}

