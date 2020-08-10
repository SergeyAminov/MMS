package com.aminov.model;

import com.aminov.dto.ProductDto;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("session")
public class Cart {

    private List<ProductDto> productList;

    public Cart(){
        this.productList = new ArrayList<>();
    }

    public void addItem(ProductDto product){
        this.productList.add(product);
    }

    public void removeItem(ProductDto product){
        this.productList.remove(product);
    }

    public List<ProductDto> getItems(){
        return this.productList;
    }

    public void clearCart(){
        this.productList.clear();
    }

}
