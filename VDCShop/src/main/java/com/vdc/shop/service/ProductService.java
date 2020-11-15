package com.vdc.shop.service;

import com.vdc.shop.dto.PageProductResponseDto;
import com.vdc.shop.dto.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final RestTemplate restTemplate;
    private final RecordService recordService;

    @Value("${service.product.url}")
    private String productUrl;

    @Autowired
    public ProductService(RestTemplate restTemplate, RecordService recordService) {
        this.restTemplate = restTemplate;
        this.recordService = recordService;
    }

    public PageProductResponseDto retrievePageProduct(String name, String color, String brandName, List<Long> prices, List<String> sortBy,
                                                      String sortDirection, int page, int pageSize) {
        recordService.recordCustomerSearchBehavior(name, color, brandName, prices, sortBy, sortDirection);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(productUrl)
                .queryParamIfPresent("name", Optional.ofNullable(name))
                .queryParamIfPresent("color", Optional.ofNullable(color))
                .queryParamIfPresent("brandName", Optional.ofNullable(brandName))
                .queryParamIfPresent("prices", Optional.ofNullable(prices))
                .queryParamIfPresent("sortBy", Optional.ofNullable(sortBy))
                .queryParamIfPresent("sortDirection", Optional.ofNullable(sortDirection))
                .queryParamIfPresent("page", Optional.of(page))
                .queryParamIfPresent("pageSize", Optional.of(pageSize));
        ResponseEntity<PageProductResponseDto> responseEntity = restTemplate.getForEntity(builder.toUriString(), PageProductResponseDto.class);
        return responseEntity.getBody();
    }

    public ProductResponseDto retrieveProductById(String id) {
        String url = productUrl + "/" + id;
        ResponseEntity<ProductResponseDto> responseEntity = restTemplate.getForEntity(url, ProductResponseDto.class);
        recordService.recordCustomerViewBehavior(id);
        return responseEntity.getBody();
    }


}
