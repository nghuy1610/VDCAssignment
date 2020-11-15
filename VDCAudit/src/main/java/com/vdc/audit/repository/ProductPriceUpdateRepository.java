package com.vdc.audit.repository;

import com.vdc.audit.entity.ProductPriceUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPriceUpdateRepository extends JpaRepository<ProductPriceUpdate, String> {
    Page<ProductPriceUpdate> findByProductId(String productId, Pageable pageable);
}
