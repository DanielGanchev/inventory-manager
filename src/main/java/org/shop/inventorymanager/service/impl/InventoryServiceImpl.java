package org.shop.inventorymanager.service.impl;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.shop.inventorymanager.models.entities.Inventory;
import org.shop.inventorymanager.models.entities.Product;
import org.shop.inventorymanager.models.entities.ProductInfo;
import org.shop.inventorymanager.models.entities.User;
import org.shop.inventorymanager.repository.InventoryRepository;
import org.shop.inventorymanager.repository.ProductInfoRepository;
import org.shop.inventorymanager.repository.ProductRepository;
import org.shop.inventorymanager.repository.UserRepository;
import org.shop.inventorymanager.service.InventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

  private final UserRepository userRepository;
  private final ProductRepository productRepository;
  private final ProductInfoRepository productInfoRepository;
  private final InventoryRepository inventoryRepository;


  //Add Product Wrapper to inventory
  public boolean addProductToInventory(Long productId, Long inventoryId) {
    ProductInfo productInfo = productInfoRepository.findById(productId).orElseThrow();
    Inventory inventory = inventoryRepository.findById(inventoryId).orElseThrow();

    boolean productExists = inventory.getProducts().stream()
        .anyMatch(product -> product.getProductInfo().getId().equals(productInfo.getId()));

    if (productExists) {
      throw new IllegalArgumentException("Product already exists in inventory");
    }

    Product product = new Product();
    product.setProductInfo(productInfo);
    product.setInventory(inventory);
    productRepository.save(product);

    return true;
  }



  //Remove Product Wrapper from inventory
  @Transactional
  public void removeProductFromInventory(Long userId, Long productId) {
    Product product = productRepository.findById(productId).orElseThrow();



    boolean productExists = getInventoryProductsByUserId(userId).stream()
        .anyMatch(product1 -> product1.getId().equals(productId));

    if (!productExists) {
      throw new IllegalArgumentException("User does not have this product in inventory");
    }

    productRepository.delete(product);
  }

  //Get all products in inventory by inventory id
  public Set<Product> getInventoryProducts(Long inventoryId) {
    Inventory inventory = inventoryRepository.findById(inventoryId).orElseThrow();

    return inventory.getProducts();
  }

  //Get all products in inventory by user id
  public Set<Product> getInventoryProductsByUserId(Long userId) {
    User user = userRepository.findById(userId).orElseThrow();

    return user.getInventory().getProducts();
  }





}

