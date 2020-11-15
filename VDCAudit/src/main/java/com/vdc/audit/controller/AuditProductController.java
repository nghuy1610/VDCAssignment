package com.vdc.audit.controller;

import com.vdc.audit.dto.request.ProductPriceUpdateRequestDto;
import com.vdc.audit.dto.response.PageProductPriceUpdateResponseDto;
import com.vdc.audit.dto.response.ProductPriceUpdateResponseDto;
import com.vdc.audit.exception.BaseException;
import com.vdc.audit.service.ProductPriceUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/audits/products")
public class AuditProductController {
    private final ProductPriceUpdateService productPriceUpdateService;

    @Autowired
    public AuditProductController(ProductPriceUpdateService productPriceUpdateService) {
        this.productPriceUpdateService = productPriceUpdateService;
    }

    @PostMapping(path = "/price-updates", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createProductPrice(@RequestBody ProductPriceUpdateRequestDto requestDto) {
        ProductPriceUpdateResponseDto responseDto = productPriceUpdateService.createAndRetrieveProductPriceUpdate(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping(path = "/price-updates/{id}", produces = "application/json")
    public ResponseEntity<?> retrieveProductPrice(@PathVariable String id) throws BaseException {
        ProductPriceUpdateResponseDto responseDto = productPriceUpdateService.retrieveById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(path = "{id}//price-updates", produces = "application/json")
    public ResponseEntity<?> retrievePageProductPrices(@PathVariable ("id") String productId,
                                                      @RequestParam (defaultValue = "0") int page,
                                                      @RequestParam (defaultValue = "10") int pageSize) {
        PageProductPriceUpdateResponseDto responseDto = productPriceUpdateService.retrievePageProductPriceUpdate(productId, page, pageSize);
        return ResponseEntity.ok(responseDto);
    }
}
