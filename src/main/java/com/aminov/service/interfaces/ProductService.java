package com.aminov.service.interfaces;

import com.aminov.dto.ProductDto;

import java.util.List;
import java.util.Map;

public interface ProductService <T> extends InterfaceService<T> {
    List<T> getFilteredItems(Map<String, String> params);
}