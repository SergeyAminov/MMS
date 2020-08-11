package com.aminov.model;

import com.aminov.dto.ProductDto;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("session")
public class Cart {

    private List<ProductDto> itemList;

    public Cart(){
        this.itemList = new ArrayList<>();
    }

    public Cart(Cart cart) {
        this.itemList = cart.itemList;
    }

    public List<ProductDto> getItemList() {
        return itemList;
    }

    public void setItemList(List<ProductDto> itemList) {
        this.itemList = itemList;
    }

    public void addItem(ProductDto product){
        this.itemList.add(product);
    }

    public void removeItem(ProductDto product){
        this.itemList.removeIf(item -> item.equals(product));

    }

    public List<ProductDto> getItems(){
        return this.itemList;
    }

    public void clearCart(){
        this.itemList.clear();
    }

}
