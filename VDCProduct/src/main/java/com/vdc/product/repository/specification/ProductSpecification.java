package com.vdc.product.repository.specification;

import com.vdc.product.entity.Brand_;
import com.vdc.product.entity.Product;
import com.vdc.product.entity.Product_;
import com.vdc.product.entity.StringAttribute;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.List;

@Component
public class ProductSpecification {
    public Specification<Product> productHasName(String name) {
        return (root, query, builder) -> builder.like(root.get(Product_.name), "%" + name + "%");
    }

    public Specification<Product> productHasPriceIn(long lowerPrice, long higherPrice) {
        return (root, query, builder) -> builder.and(builder.ge(root.get(Product_.price), lowerPrice), builder.le(root.get(Product_.price), higherPrice));
    }

    public Specification<Product> productExist() {
        return (root, query, builder) -> builder.isNotNull(root.get(Product_.id));
    }

    public Specification<Product> productHasBrandName(String brandName) {
        return (root, query, builder) -> builder.like(root.get(Product_.brand).get(Brand_.name), "%" + brandName + "%");
    }

    public Specification<Product> productHasColor(String color) {
        return (root, query, builder) -> {
          Join<Product, StringAttribute> stringAttributes = root.join("stringAttributes", JoinType.LEFT);
          return builder.equal(stringAttributes.get("value"), color);
        };
    }

}
