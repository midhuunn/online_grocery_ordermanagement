package com.midhunn.grocery.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private CustomerOrder order;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "grocery_item_id", nullable = false)
    private GroceryItem groceryItem;
    private Integer quantity;
    @Column(precision=12, scale=2)
    private BigDecimal unitPrice;

    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public CustomerOrder getOrder(){return order;} public void setOrder(CustomerOrder order){this.order=order;}
    public GroceryItem getGroceryItem(){return groceryItem;} public void setGroceryItem(GroceryItem groceryItem){this.groceryItem=groceryItem;}
    public Integer getQuantity(){return quantity;} public void setQuantity(Integer quantity){this.quantity=quantity;}
    public BigDecimal getUnitPrice(){return unitPrice;} public void setUnitPrice(BigDecimal unitPrice){this.unitPrice=unitPrice;}
}
