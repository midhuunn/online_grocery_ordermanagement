package com.midhunn.grocery.repository;
import com.midhunn.grocery.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {}
