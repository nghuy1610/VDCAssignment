package com.vdc.product.entity;

import com.vdc.product.enums.ProductCategory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@NamedEntityGraph(name = "Product.brand", attributeNodes = @NamedAttributeNode("brand"))
public class Product extends BaseEntity{

    private String name;
    private long price;

    @Enumerated(EnumType.STRING)
    ProductCategory category;

    @ManyToOne
    private Brand brand;

    @OneToMany(mappedBy = "product")
    List<IntAttribute> intAttributes;

    @OneToMany(mappedBy = "product")
    List<StringAttribute> stringAttributes;

    @OneToMany(mappedBy = "product")
    List<DoubleAttribute> doubleAttributes;
}
