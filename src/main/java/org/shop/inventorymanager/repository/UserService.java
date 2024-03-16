package org.shop.inventorymanager.repository;

import org.shop.inventorymanager.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService extends JpaRepository<User, Long>{

}
