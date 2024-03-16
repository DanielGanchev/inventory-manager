package org.shop.inventorymanager.models.entities;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;


import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;


import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name = "inventory_products",
      joinColumns = @JoinColumn(name = "inventory_id"),
      inverseJoinColumns = @JoinColumn(name = "product_id"))
  private Set<Product> products = new HashSet<>();



}
