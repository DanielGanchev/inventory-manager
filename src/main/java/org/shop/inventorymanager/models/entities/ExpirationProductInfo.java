package org.shop.inventorymanager.models.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Data

public class ExpirationProductInfo extends ProductInfo {

  private int expirationDays;

  public int getExpirationDays() {
    return expirationDays;
  }

  public void setExpirationDays(int expirationDays) {
    this.expirationDays = expirationDays;
  }

}
