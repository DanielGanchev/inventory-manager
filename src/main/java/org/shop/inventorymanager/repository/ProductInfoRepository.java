package org.shop.inventorymanager.repository;

import java.util.Optional;
import org.shop.inventorymanager.models.entities.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Long> {

Optional<ProductInfo> findByName(String productName);





}