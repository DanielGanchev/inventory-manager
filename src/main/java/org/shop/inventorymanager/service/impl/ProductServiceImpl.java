package org.shop.inventorymanager.service.impl;


import java.math.BigDecimal;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.shop.inventorymanager.models.dtos.ProductInfoCreateDto;
import org.shop.inventorymanager.models.entities.Category;
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


  public boolean createProductInfo(ProductInfoCreateDto productInfoCreateDto) {
    ProductInfo productInfo = new ProductInfo();
    Optional<Category> category = categoryRepository.findByName(productInfoCreateDto.category());
    if (category.isPresent()) {
      productInfo.setCategory(category.get());
    } else {
      return false;
    }
    productInfo.setName(productInfoCreateDto.name());
    productInfo.setBarcode(productInfoCreateDto.barcode());
    productInfo.setPrice(BigDecimal.valueOf(productInfoCreateDto.price()));
    productInfo.setCategory(category.get());
    productInfo.setDescription(productInfoCreateDto.description());

    productInfoRepository.save(productInfo);
    return true;
  }





  public ProductInfo getProductInfoById(Long id) {
    return productInfoRepository.findById(id).orElseThrow();
  }

  public ProductInfo getProductInfoByName(String productName) {
    return productInfoRepository.findByName(productName).orElseThrow();
  }

  public ProductInfo getProductInfoByBarcode(String barcode) {
    return productInfoRepository.findByBarcode(barcode).orElseThrow();
  }


}
