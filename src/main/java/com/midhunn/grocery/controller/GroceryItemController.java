package com.midhunn.grocery.controller;

import com.midhunn.grocery.entity.GroceryItem;
import com.midhunn.grocery.exception.ResourceNotFoundException;
import com.midhunn.grocery.repository.GroceryItemRepository;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/items")
public class GroceryItemController {
    private final GroceryItemRepository repo;
    public GroceryItemController(GroceryItemRepository repo){this.repo=repo;}

    @PostMapping public ResponseEntity<GroceryItem> create(@Valid @RequestBody GroceryItem item){
        item.setId(null); return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(item));
    }
    @GetMapping public List<GroceryItem> all(){return repo.findAll();}
    @GetMapping("/{id}") public GroceryItem get(@PathVariable Long id){return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Grocery item not found: "+id));}
    @PutMapping("/{id}") public GroceryItem update(@PathVariable Long id,@Valid @RequestBody GroceryItem input){
        GroceryItem i=get(id); i.setName(input.getName()); i.setCategory(input.getCategory()); i.setPrice(input.getPrice()); i.setQuantity(input.getQuantity()); return repo.save(i);
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){GroceryItem i=get(id); repo.delete(i); return ResponseEntity.noContent().build();}
}
