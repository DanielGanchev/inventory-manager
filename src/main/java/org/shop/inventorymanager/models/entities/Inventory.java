package org.shop.inventorymanager.models.entities;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;


import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;


import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.*;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Data
@Table(name = "inventories")
public class Inventory extends BaseEntity{

  @ManyToMany
  @JoinTable(
      name = "inventory_product",
      joinColumns = @JoinColumn(name = "inventory_id"),
      inverseJoinColumns = @JoinColumn(name = "product_id"))
  private Set<Product> products = new HashSet<>();

  @Column(nullable = false)
  private Integer quantityInStock;

  @Column(nullable = false)
  private Integer quantityReserved;

  @Column(nullable = false)
  private Integer quantitySold;

  @OneToOne
  private User user;

}
