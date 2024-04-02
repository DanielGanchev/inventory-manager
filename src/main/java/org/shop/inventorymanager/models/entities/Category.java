package org.shop.inventorymanager.models.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import lombok.*;
import org.shop.inventorymanager.models.enums.CategoryEnum;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Data
@Table(name = "categories")
public class Category extends BaseEntity{

  @NotNull
  @Enumerated(EnumType.STRING)
  private CategoryEnum name;

  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<ProductInfo> productInfo = new HashSet<>();

}
