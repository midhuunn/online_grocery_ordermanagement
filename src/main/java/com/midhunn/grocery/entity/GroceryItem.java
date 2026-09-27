package com.midhunn.grocery.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "grocery_items")
public class GroceryItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank private String name;
    @NotBlank private String category;
    @NotNull @DecimalMin("0.0") @Column(precision=12, scale=2)
    private BigDecimal price;
    @NotNull @Min(0) private Integer quantity;

    public GroceryItem() {}
    public GroceryItem(String name, String category, BigDecimal price, Integer quantity) {
        this.name=name; this.category=category; this.price=price; this.quantity=quantity;
    }
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getName(){return name;} public void setName(String name){this.name=name;}
    public String getCategory(){return category;} public void setCategory(String category){this.category=category;}
    public BigDecimal getPrice(){return price;} public void setPrice(BigDecimal price){this.price=price;}
    public Integer getQuantity(){return quantity;} public void setQuantity(Integer quantity){this.quantity=quantity;}
}
