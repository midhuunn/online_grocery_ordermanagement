package com.midhunn.grocery.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "customers", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank private String name;
    @Email @NotBlank @Column(nullable = false, unique = true)
    private String email;
    @NotBlank private String address;
    @NotBlank private String phone;

    public Customer() {}
    public Customer(String name, String email, String address, String phone) {
        this.name=name; this.email=email; this.address=address; this.phone=phone;
    }
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getName(){return name;} public void setName(String name){this.name=name;}
    public String getEmail(){return email;} public void setEmail(String email){this.email=email;}
    public String getAddress(){return address;} public void setAddress(String address){this.address=address;}
    public String getPhone(){return phone;} public void setPhone(String phone){this.phone=phone;}
}
