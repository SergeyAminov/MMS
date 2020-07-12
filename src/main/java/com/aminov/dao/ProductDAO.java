package com.aminov.dao;

import com.aminov.model.Product;
import java.util.List;

public interface ProductDAO {
    List<Product> allProducts();
}