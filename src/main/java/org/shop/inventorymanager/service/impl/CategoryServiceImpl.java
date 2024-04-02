package org.shop.inventorymanager.service.impl;

import java.util.Set;
import org.shop.inventorymanager.models.entities.Category;
import org.shop.inventorymanager.models.entities.Product;
import org.shop.inventorymanager.models.entities.ProductInfo;
import org.shop.inventorymanager.repository.CategoryRepository;
import org.shop.inventorymanager.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements  CategoryService {

  private final CategoryRepository categoryRepository;

  public CategoryServiceImpl(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  //Get all products by category
  public Set<ProductInfo> getProductInfoByCategory(String category) {
    Category categoryEntity = categoryRepository.findByName(category).orElseThrow();

    return categoryEntity.getProductInfo();
  }







}
