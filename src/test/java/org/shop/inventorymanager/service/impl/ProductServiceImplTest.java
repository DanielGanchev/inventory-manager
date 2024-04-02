package org.shop.inventorymanager.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.shop.inventorymanager.models.dtos.ProductInfoCreateDto;
import org.shop.inventorymanager.models.dtos.ProductInfoUpdateDto;
import org.shop.inventorymanager.models.entities.Category;
import org.shop.inventorymanager.models.entities.Product;
import org.shop.inventorymanager.models.entities.ProductInfo;
import org.shop.inventorymanager.models.enums.CategoryEnum;
import org.shop.inventorymanager.repository.CategoryRepository;
import org.shop.inventorymanager.repository.ProductInfoRepository;
import org.shop.inventorymanager.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

  @Mock
  private ProductRepository productRepository;

  @Mock
  private ProductInfoRepository productInfoRepository;

  @Mock
  private CategoryRepository categoryRepository;

  @InjectMocks
  private ProductServiceImpl productService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void createProductInfoSuccessfully() {
    ProductInfoCreateDto productInfoCreateDto = new ProductInfoCreateDto("Laptop","High performance laptop",1000.0,"ELECTRONICS");

    Category category = new Category();
    category.setName(CategoryEnum.ELECTRONICS);

    when(categoryRepository.findByName(anyString())).thenReturn(Optional.of(category));
    when(productInfoRepository.save(any(ProductInfo.class))).thenAnswer(i -> i.getArguments()[0]);

    assertTrue(productService.createProductInfo(productInfoCreateDto));
    verify(productInfoRepository, times(1)).save(any(ProductInfo.class));
  }

  @Test
  void createProductInfoFailsWhenCategoryNotFound() {
    ProductInfoCreateDto productInfoCreateDto = new ProductInfoCreateDto("Laptop","High performance laptop",1000.0,"NonexistentCategory");


    when(categoryRepository.findByName(anyString())).thenReturn(Optional.empty());

    assertFalse(productService.createProductInfo(productInfoCreateDto));
    verify(productInfoRepository, times(0)).save(any(ProductInfo.class));
  }

  @Test
  void updateProductQuantitySuccessfully() {
    Product product = new Product();
    product.setQuantityInStock(10);

    when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

    productService.updateProductQuantity(1L, 20);

    assertEquals(20, product.getQuantityInStock());
    verify(productRepository, times(1)).save(product);
  }

  @Test
  void updateProductQuantitySoldSuccessfully() {
    Product product = new Product();
    product.setQuantitySold(10);

    when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

    productService.updateProductQuantitySold(1L, 20);

    assertEquals(20, product.getQuantitySold());
    verify(productRepository, times(1)).save(product);
  }

  @Test
  void updateProductInfoSuccessfully() {
    ProductInfo productInfo = new ProductInfo();
    productInfo.setId(1L);
    productInfo.setName("Laptop");
    productInfo.setDescription("High performance laptop");
    productInfo.setPrice(BigDecimal.valueOf(1000.0));
    Category category = new Category();
    category.setName(CategoryEnum.ELECTRONICS);
    productInfo.setCategory(category);

    ProductInfoUpdateDto productInfoUpdateDto = new ProductInfoUpdateDto(1L, "Laptop Pro", "High performance laptop", 1500.0,"ELECTRONICS");

    when(productInfoRepository.findById(anyLong())).thenReturn(Optional.of(productInfo));

    productService.updateProductInfo(productInfoUpdateDto);

    assertEquals("Laptop Pro", productInfo.getName());
    assertEquals("High performance laptop", productInfo.getDescription());
    assertEquals(BigDecimal.valueOf(1500.0), productInfo.getPrice());
    assertEquals(CategoryEnum.ELECTRONICS, productInfo.getCategory().getName());
    verify(productInfoRepository, times(1)).save(productInfo);
  }
}