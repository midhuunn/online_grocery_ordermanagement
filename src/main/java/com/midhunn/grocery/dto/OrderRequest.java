package com.midhunn.grocery.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class OrderRequest {
    @NotNull private Long customerId;
    @NotEmpty @Valid private List<OrderLineRequest> items;
    public Long getCustomerId(){return customerId;} public void setCustomerId(Long customerId){this.customerId=customerId;}
    public List<OrderLineRequest> getItems(){return items;} public void setItems(List<OrderLineRequest> items){this.items=items;}

    public static class OrderLineRequest {
        @NotNull private Long groceryItemId;
        @NotNull @jakarta.validation.constraints.Min(1) private Integer quantity;
        public Long getGroceryItemId(){return groceryItemId;} public void setGroceryItemId(Long groceryItemId){this.groceryItemId=groceryItemId;}
        public Integer getQuantity(){return quantity;} public void setQuantity(Integer quantity){this.quantity=quantity;}
    }
}
