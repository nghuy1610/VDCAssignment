package com.vdc.shop.controller;

import com.vdc.shop.dto.PageProductResponseDto;
import com.vdc.shop.dto.ProductResponseDto;
import com.vdc.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "", produces = "application/json")
    public ResponseEntity<?> retrievePageProduct(@RequestParam(required = false) String name,
                                                 @RequestParam (required = false) String color,
                                                 @RequestParam (required = false) String brandName,
                                                 @RequestParam (required = false) List<Long> prices,
                                                 @RequestParam (required = false) List<String> sortBy,
                                                 @RequestParam (required = false) String sortDirection,
                                                 @RequestParam (defaultValue = "0") int page,
                                                 @RequestParam (defaultValue = "10") int pageSize) {
        PageProductResponseDto dto = productService.retrievePageProduct(name, color, brandName, prices, sortBy, sortDirection, page, pageSize);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<?> retrieveProductById(@PathVariable String id) {
        ProductResponseDto dto = productService.retrieveProductById(id);
        return ResponseEntity.ok(dto);
    }
}
