package com.midhunn.grocery.repository;
import com.midhunn.grocery.entity.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;
public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {}
