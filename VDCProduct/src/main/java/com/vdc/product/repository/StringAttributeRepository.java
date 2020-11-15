package com.vdc.product.repository;

import com.vdc.product.entity.StringAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StringAttributeRepository extends JpaRepository<StringAttribute, String> {
}
