package org.shop.inventorymanager.service.impl;

import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.shop.inventorymanager.models.entities.Inventory;
import org.shop.inventorymanager.models.entities.Product;
import org.shop.inventorymanager.models.entities.ProductInfo;
import org.shop.inventorymanager.models.entities.User;
import org.shop.inventorymanager.repository.InventoryRepository;
import org.shop.inventorymanager.repository.ProductInfoRepository;
import org.shop.inventorymanager.repository.ProductRepository;
import org.shop.inventorymanager.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class InventoryServiceImplTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private ProductRepository productRepository;

  @Mock
  private ProductInfoRepository productInfoRepository;

  @Mock
  private InventoryRepository inventoryRepository;

  @InjectMocks
  private InventoryServiceImpl inventoryService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void addProductToInventorySuccessfully() {
    when(productInfoRepository.findById(any())).thenReturn(Optional.of(new ProductInfo()));
    when(inventoryRepository.findById(any())).thenReturn(Optional.of(new Inventory()));

    assertTrue(inventoryService.addProductToInventory(1L, 1L));
    verify(productRepository, times(1)).save(any());
  }

  @Test
  public void addProductToInventoryThrowsExceptionWhenProductExists() {
    ProductInfo productInfo = new ProductInfo();
    productInfo.setId(1L);
    Product product = new Product();
    product.setProductInfo(productInfo);
    Inventory inventory = new Inventory();
    inventory.setProducts(Collections.singleton(product));

    when(productInfoRepository.findById(any())).thenReturn(Optional.of(productInfo));
    when(inventoryRepository.findById(any())).thenReturn(Optional.of(inventory));

    assertThrows(IllegalArgumentException.class, () -> inventoryService.addProductToInventory(1L, 1L));
    verify(productRepository, times(0)).save(any());
  }

  @Test
  public void removeProductFromInventorySuccessfully() {
    User user = new User();
    Product product = new Product();
    product.setId(1L);
    user.setInventory(new Inventory());
    user.getInventory().setProducts(Collections.singleton(product));

    when(userRepository.findById(any())).thenReturn(Optional.of(user));
    when(productRepository.findById(any())).thenReturn(Optional.of(product));

    inventoryService.removeProductFromInventory(1L, 1L);
    verify(productRepository, times(1)).delete(any());
  }

  @Test
  public void removeProductFromInventoryThrowsExceptionWhenProductNotInInventory() {
    User user = new User();
    user.setInventory(new Inventory());

    when(userRepository.findById(any())).thenReturn(Optional.of(user));
    when(productRepository.findById(any())).thenReturn(Optional.of(new Product()));

    assertThrows(IllegalArgumentException.class, () -> inventoryService.removeProductFromInventory(1L, 1L));
    verify(productRepository, times(0)).delete(any());
  }
}