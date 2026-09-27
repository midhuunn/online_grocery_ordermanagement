package com.midhunn.grocery.controller;

import com.midhunn.grocery.dto.OrderRequest;
import com.midhunn.grocery.entity.*;
import com.midhunn.grocery.exception.ResourceNotFoundException;
import com.midhunn.grocery.repository.*;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final CustomerOrderRepository orderRepo;
    private final CustomerRepository customerRepo;
    private final GroceryItemRepository itemRepo;
    public OrderController(CustomerOrderRepository orderRepo, CustomerRepository customerRepo, GroceryItemRepository itemRepo){
        this.orderRepo=orderRepo; this.customerRepo=customerRepo; this.itemRepo=itemRepo;
    }

    @PostMapping @Transactional
    public ResponseEntity<CustomerOrder> create(@Valid @RequestBody OrderRequest request){
        Customer customer=customerRepo.findById(request.getCustomerId())
            .orElseThrow(()->new ResourceNotFoundException("Customer not found: "+request.getCustomerId()));
        CustomerOrder order=new CustomerOrder(); order.setCustomer(customer);
        List<OrderItem> lines=new ArrayList<>(); BigDecimal total=BigDecimal.ZERO;
        for(OrderRequest.OrderLineRequest line:request.getItems()){
            GroceryItem item=itemRepo.findById(line.getGroceryItemId())
                .orElseThrow(()->new ResourceNotFoundException("Grocery item not found: "+line.getGroceryItemId()));
            OrderItem oi=new OrderItem(); oi.setOrder(order); oi.setGroceryItem(item);
            oi.setQuantity(line.getQuantity()); oi.setUnitPrice(item.getPrice());
            lines.add(oi); total=total.add(item.getPrice().multiply(BigDecimal.valueOf(line.getQuantity())));
        }
        order.setItems(lines); order.setTotalPrice(total);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderRepo.save(order));
    }
    @GetMapping public List<CustomerOrder> all(){return orderRepo.findAll();}
    @GetMapping("/{id}") public CustomerOrder get(@PathVariable Long id){return orderRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Order not found: "+id));}

    @PutMapping("/{id}") @Transactional
    public CustomerOrder update(@PathVariable Long id,@Valid @RequestBody OrderRequest request){
        CustomerOrder order=get(id);
        Customer customer=customerRepo.findById(request.getCustomerId())
            .orElseThrow(()->new ResourceNotFoundException("Customer not found: "+request.getCustomerId()));
        order.setCustomer(customer);
        order.getItems().clear();
        BigDecimal total=BigDecimal.ZERO;
        for(OrderRequest.OrderLineRequest line:request.getItems()){
            GroceryItem item=itemRepo.findById(line.getGroceryItemId())
                .orElseThrow(()->new ResourceNotFoundException("Grocery item not found: "+line.getGroceryItemId()));
            OrderItem oi=new OrderItem(); oi.setOrder(order); oi.setGroceryItem(item);
            oi.setQuantity(line.getQuantity()); oi.setUnitPrice(item.getPrice()); order.getItems().add(oi);
            total=total.add(item.getPrice().multiply(BigDecimal.valueOf(line.getQuantity())));
        }
        order.setTotalPrice(total); return orderRepo.save(order);
    }
    @DeleteMapping("/{id}") @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id){CustomerOrder o=get(id); orderRepo.delete(o); return ResponseEntity.noContent().build();}
}
