package com.vdc.product.repository;

import com.vdc.product.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, String> {
}
