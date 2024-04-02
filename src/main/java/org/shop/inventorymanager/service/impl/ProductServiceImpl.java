package org.shop.inventorymanager.service.impl;


import java.math.BigDecimal;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.shop.inventorymanager.models.dtos.ProductInfoCreateDto;
import org.shop.inventorymanager.models.dtos.ProductInfoUpdateDto;
import org.shop.inventorymanager.models.entities.Category;
import org.shop.inventorymanager.models.entities.Product;
import org.shop.inventorymanager.models.entities.ProductInfo;
import org.shop.inventorymanager.repository.CategoryRepository;
import org.shop.inventorymanager.repository.ProductInfoRepository;
import org.shop.inventorymanager.repository.ProductRepository;
import org.shop.inventorymanager.service.ProductService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductInfoRepository productInfoRepository;

  private final CategoryRepository categoryRepository;

//Create Product Information
  public boolean createProductInfo(ProductInfoCreateDto productInfoCreateDto) {
    ProductInfo productInfo = new ProductInfo();
    Optional<Category> category = categoryRepository.findByName(productInfoCreateDto.category());
    if (category.isPresent()) {
      productInfo.setCategory(category.get());
    } else {
      return false;
    }
    productInfo.setName(productInfoCreateDto.name());
    productInfo.setPrice(BigDecimal.valueOf(productInfoCreateDto.price()));
    productInfo.setCategory(category.get());
    productInfo.setDescription(productInfoCreateDto.description());

    productInfoRepository.save(productInfo);
    return true;
  }

//Get Product Information by id
  public ProductInfo getProductInfoById(Long id) {
    return productInfoRepository.findById(id).orElseThrow();
  }

  //Get Product Information by name
  public ProductInfo getProductInfoByName(String productName) {
    return productInfoRepository.findByName(productName).orElseThrow();
  }


  //Update Product  quantity in the inventory
  public void updateProductQuantity(Long productId, int quantity) {
    Product product = productRepository.findById(productId).orElseThrow();
    product.setQuantityInStock(quantity);
    productRepository.save(product);
  }

  //Update product quantity sold in the inventory
  public void updateProductQuantitySold(Long productId, int quantity) {
    Product product = productRepository.findById(productId).orElseThrow();
    product.setQuantitySold(quantity);
    productRepository.save(product);
  }

  //Update Product Information
  public void updateProductInfo(ProductInfoUpdateDto productInfo) {
    ProductInfo productInfoEntity = productInfoRepository.findById(productInfo.id()).orElseThrow();
    productInfoEntity.setName(productInfo.name());
    productInfoEntity.setDescription(productInfo.description());
    productInfoEntity.setPrice(BigDecimal.valueOf(productInfo.price()));
    productInfoRepository.save(productInfoEntity);
  }


}
