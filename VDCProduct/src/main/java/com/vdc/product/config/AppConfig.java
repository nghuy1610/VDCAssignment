package com.vdc.product.config;

import com.vdc.product.dto.DoubleAttributeDto;
import com.vdc.product.dto.StringAttributeDto;
import com.vdc.product.dto.request.BrandRequestDto;
import com.vdc.product.dto.request.ProductRequestDto;
import com.vdc.product.entity.Brand;
import com.vdc.product.enums.ProductCategory;
import com.vdc.product.service.BrandService;
import com.vdc.product.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    CommandLineRunner initDB(BrandService brandService, ProductService productService) {
        return args -> {
            String brandName = "brand";
            List<BrandRequestDto> brandRequestDtoList = new ArrayList<>();
            BrandRequestDto requestDto = new BrandRequestDto(brandName);
            brandRequestDtoList.add(requestDto);
            for (int i = 0; i < 10; i++) {
                String name = brandName + (i % 2);
                requestDto = new BrandRequestDto(name);
                brandRequestDtoList.add(requestDto);
            }
            List<Brand> brands = brandService.createBrands(brandRequestDtoList);

            List<ProductRequestDto> productRequestDtoList = new ArrayList<>();
            String productName = "product";
            int price = 125000;
            for (int i = 0; i < brands.size(); i++) {
                Brand brand = brands.get(i);
                ProductRequestDto productRequestDto = new ProductRequestDto();
                productRequestDto.setBrandId(brand.getId());
                productRequestDto.setPrice(price + i * 1000);
                productRequestDto.setCategory(i % 3 == 0 ? ProductCategory.BOOK.name() : ProductCategory.LAPTOP.name());
                productRequestDto.setName(productName + brand.getName());
                StringAttributeDto stringAttributeDto = new StringAttributeDto("color", i % 2 == 0 ? "Red" : "Blue");
                DoubleAttributeDto doubleAttributeDto = new DoubleAttributeDto("weight", i % 2 == 0 ? 1.2 : 2.5);
                productRequestDto.getDoubleAttributes().add(doubleAttributeDto);
                productRequestDto.getStringAttributes().add(stringAttributeDto);
                productRequestDtoList.add(productRequestDto);
            }
            productService.createProducts(productRequestDtoList);
        };
    }
}
