package com.vdc.audit.controller;

import com.vdc.audit.dto.request.CustomerBehaviorRequestDto;
import com.vdc.audit.dto.response.CustomerBehaviorResponseDto;
import com.vdc.audit.dto.response.PageCustomerBehaviorResponseDto;
import com.vdc.audit.exception.BaseException;
import com.vdc.audit.service.CustomerBehaviorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/audits/customers")
public class AuditCustomerController {

    private final CustomerBehaviorService customerBehaviorService;

    @Autowired
    public AuditCustomerController(CustomerBehaviorService customerBehaviorService) {
        this.customerBehaviorService = customerBehaviorService;
    }

    @PostMapping(path = "/behaviors", consumes = "application/json" , produces = "application/json")
    public ResponseEntity<?> createCustomerBehavior(@RequestBody CustomerBehaviorRequestDto requestDto) {
        CustomerBehaviorResponseDto responseDto = customerBehaviorService.createdAndRetrieveCustomerBehavior(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping(path = "/behaviors/{id}", produces = "application/json")
    public ResponseEntity<?> retrieveCustomerBehaviorById(@PathVariable String id) throws BaseException {
        CustomerBehaviorResponseDto responseDto = customerBehaviorService.retrieveById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(path = "/{id}/behaviors", produces = "application/json")
    public ResponseEntity<?> retrievePageCustomerBehaviorByCustomerId(@PathVariable ("id") String customerId,
                                                          @RequestParam (defaultValue = "0") int page,
                                                          @RequestParam (defaultValue = "10") int pageSize) {
        PageCustomerBehaviorResponseDto responseDto = customerBehaviorService.retrievePageCustomerBehavior(customerId, page, pageSize);
        return ResponseEntity.ok(responseDto);
    }
}
