package com.vdc.product.repository;

import com.vdc.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {
    @EntityGraph(value = "Product.brand", type = EntityGraph.EntityGraphType.FETCH)
    Page<Product> findAll(Specification<Product> specification, Pageable pageable);
}
