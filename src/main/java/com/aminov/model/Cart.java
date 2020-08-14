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
        for (ProductDto productDto : this.itemList) {
            if (productDto.equals(product)) {
                this.itemList.remove(productDto);
                break;
            }
        }
    }

}
