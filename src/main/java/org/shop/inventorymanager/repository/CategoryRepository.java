package org.shop.inventorymanager.repository;

import java.util.Optional;
import org.shop.inventorymanager.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

 Optional<Category> findByName(String category);
}