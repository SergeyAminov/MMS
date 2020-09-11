package com.aminov.service.interfaces;

import java.util.List;
import java.util.Map;

public interface ProductService <T> extends InterfaceService<T> {
    List<T> getFilteredItems(Map<String, String> params);
    String convertToJSON(T t);
    String convertToJSON(List<T> t);
}