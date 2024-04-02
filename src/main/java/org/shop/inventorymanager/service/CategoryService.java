package org.shop.inventorymanager.service;

import java.util.Set;
import org.shop.inventorymanager.models.entities.ProductInfo;

public interface CategoryService {

  Set<ProductInfo> getProductInfoByCategory(String category);

}
