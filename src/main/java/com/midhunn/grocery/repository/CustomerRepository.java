package com.midhunn.grocery.repository;
import com.midhunn.grocery.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerRepository extends JpaRepository<Customer, Long> {}
