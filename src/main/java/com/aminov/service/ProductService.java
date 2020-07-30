package com.aminov.service;

import com.aminov.dto.ProductDto;
import java.util.List;

public interface ProductService {
    List<ProductDto> allProducts();
    void add(ProductDto productDto);
    void delete(ProductDto productDto);
    void edit(ProductDto productDto);
    ProductDto getById(int id);
}