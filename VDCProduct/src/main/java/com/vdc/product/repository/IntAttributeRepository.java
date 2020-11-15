package com.vdc.product.repository;

import com.vdc.product.entity.IntAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntAttributeRepository extends JpaRepository<IntAttribute, String> {
}
