package com.aminov.service;

import com.aminov.dao.ProductDAO;
import com.aminov.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;

    @Autowired
    public void setProductDAO(ProductDAO productDAO){
        this.productDAO = productDAO;
    }

    @Override
    @Transactional
    public List<Product> allProducts() {
        return productDAO.allProducts();
    }

    @Override
    @Transactional
    public void add(Product product) {
        productDAO.add(product);
    }

    @Override
    @Transactional
    public void delete(Product product) {
        productDAO.delete(product);
    }

    @Override
    @Transactional
    public void edit(Product product) {
        productDAO.edit(product);
    }

    @Override
    @Transactional
    public Product getById(int id) {
        return productDAO.getById(id);
    }
}
