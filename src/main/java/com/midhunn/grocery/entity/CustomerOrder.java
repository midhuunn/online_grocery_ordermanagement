package com.midhunn.grocery.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class CustomerOrder {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OrderItem> items = new ArrayList<>();
    private LocalDateTime orderDate;
    @Column(precision=12, scale=2)
    private BigDecimal totalPrice;

    @PrePersist
    public void prePersist() { if (orderDate == null) orderDate = LocalDateTime.now(); }

    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Customer getCustomer(){return customer;} public void setCustomer(Customer customer){this.customer=customer;}
    public List<OrderItem> getItems(){return items;} public void setItems(List<OrderItem> items){this.items=items;}
    public LocalDateTime getOrderDate(){return orderDate;} public void setOrderDate(LocalDateTime orderDate){this.orderDate=orderDate;}
    public BigDecimal getTotalPrice(){return totalPrice;} public void setTotalPrice(BigDecimal totalPrice){this.totalPrice=totalPrice;}
}
