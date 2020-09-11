package com.aminov.controller;

import com.aminov.dto.ProductDto;
import com.aminov.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class MessageController {

    private ProductService<ProductDto> productService;

    @Autowired
    public void setProductService(ProductService<ProductDto> productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/message", produces = MediaType.APPLICATION_JSON_VALUE)
    public String sendMessage(){
        List<ProductDto> productDtoList = new ArrayList<>();
        ProductDto productDto = this.productService.getById(9);
        ProductDto productDto_2 = this.productService.getById(9);
        ProductDto productDto_3 = this.productService.getById(9);
        productDtoList.add(productDto);
        productDtoList.add(productDto_2);
        productDtoList.add(productDto_3);
        return this.productService.convertToJSON(productDtoList);
    }

}
