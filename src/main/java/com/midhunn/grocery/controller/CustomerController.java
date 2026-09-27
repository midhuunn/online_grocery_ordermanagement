package com.midhunn.grocery.controller;

import com.midhunn.grocery.entity.Customer;
import com.midhunn.grocery.exception.ResourceNotFoundException;
import com.midhunn.grocery.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerRepository repo;
    public CustomerController(CustomerRepository repo){this.repo=repo;}

    @PostMapping public ResponseEntity<Customer> create(@Valid @RequestBody Customer customer) {
        customer.setId(null); return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(customer));
    }
    @GetMapping public List<Customer> all(){return repo.findAll();}
    @GetMapping("/{id}") public Customer get(@PathVariable Long id){return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Customer not found: "+id));}
    @PutMapping("/{id}") public Customer update(@PathVariable Long id,@Valid @RequestBody Customer input){
        Customer c=get(id); c.setName(input.getName()); c.setEmail(input.getEmail()); c.setAddress(input.getAddress()); c.setPhone(input.getPhone()); return repo.save(c);
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){Customer c=get(id); repo.delete(c); return ResponseEntity.noContent().build();}
}
