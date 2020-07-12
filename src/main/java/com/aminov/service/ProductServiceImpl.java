package com.aminov.service;

import com.aminov.dao.ProductDAO;
import com.aminov.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;

    @Autowired
    public void setProductDAO(ProductDAO productDAO){
        this.productDAO = productDAO;
    }

    @Override
    public List<Product> allProducts() {
        return productDAO.allProducts();
    }
}
