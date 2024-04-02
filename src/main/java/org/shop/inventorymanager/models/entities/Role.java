package org.shop.inventorymanager.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.shop.inventorymanager.models.enums.RoleEnum;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Role extends BaseEntity {


  @Enumerated(EnumType.STRING)
  private RoleEnum name;

}
