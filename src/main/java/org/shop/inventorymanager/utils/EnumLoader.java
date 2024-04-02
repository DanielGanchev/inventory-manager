package org.shop.inventorymanager.utils;

import lombok.RequiredArgsConstructor;

import org.shop.inventorymanager.models.entities.Category;
import org.shop.inventorymanager.models.entities.Role;
import org.shop.inventorymanager.models.enums.CategoryEnum;
import org.shop.inventorymanager.models.enums.RoleEnum;
import org.shop.inventorymanager.repository.CategoryRepository;
import org.shop.inventorymanager.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnumLoader implements CommandLineRunner {

  private final RoleRepository roleRepository;
  private final CategoryRepository categoryRepository;

  @Override
  public void run(String... args) throws Exception {
    if (roleRepository.count() == 0) {
      for (RoleEnum roleEnum : RoleEnum.values()) {
        Role role = new Role();
        role.setName(roleEnum);
        roleRepository.save(role);
      }
    }

    if (categoryRepository.count() == 0) {
      for (CategoryEnum categoryEnum : CategoryEnum.values()) {
        Category category = new Category();
        category.setName(categoryEnum);
        categoryRepository.save(category);
      }
    }


  }
}
