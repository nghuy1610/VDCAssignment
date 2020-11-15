package com.vdc.audit.repository;

import com.vdc.audit.entity.CustomerBehavior;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerBehaviorRepository extends JpaRepository<CustomerBehavior, String> {
    Page<CustomerBehavior> findByCustomerId(String customerId, Pageable pageable);
}
