package org.shop.inventorymanager.models.entities;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;

import java.util.HashSet;
import java.util.Set;
import lombok.*;
import lombok.experimental.SuperBuilder;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Data
@Builder
@Table(name = "product_info")
public class ProductInfo extends BaseEntity{

  @Column(nullable = false,unique = true,length = 100)
  private String name;

  @Column(length = 500)
  private String description;

  @Column(nullable = false,precision = 10, scale = 2)
  private BigDecimal price;

  @Column(nullable = false, unique = true,length = 13)
  private String barcode;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;

  @OneToMany(mappedBy = "productInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<Product> products = new HashSet<>();

}
