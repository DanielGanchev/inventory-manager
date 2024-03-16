package org.shop.inventorymanager.models.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Data
@Builder
@Table(name = "products")
public class Product extends BaseEntity{

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "inventory_id")
  private Inventory inventory;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_info_id")
  private ProductInfo productInfo;

  private int quantityInStock = 0;
  private int quantitySold = 0;

}
