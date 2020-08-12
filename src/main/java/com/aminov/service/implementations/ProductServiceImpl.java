package com.aminov.service.implementations;

import com.aminov.dao.interfaces.ProductDAO;
import com.aminov.dto.ProductDto;
import com.aminov.mapper.ProductMapper;
import com.aminov.model.Product;
import com.aminov.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService<ProductDto> {

    private ProductDAO<Product> productDAO;
    private ProductMapper productMapper;

    @Autowired
    public void setProductDAO(ProductDAO<Product> productDAO){
        this.productDAO = productDAO;
    }

    @Autowired
    public void setProductMapper(ProductMapper productMapper){
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public List<ProductDto> allItems() {
        return this.productDAO
                .allItems()
                .stream()
                .map(this.productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void add(ProductDto productDto) {
        this.productDAO.add(this.productMapper.toEntity(productDto));
    }

    @Override
    @Transactional
    public void delete(ProductDto productDto) {
        this.productDAO.delete(this.productMapper.toEntity(productDto));
    }

    @Override
    @Transactional
    public void edit(ProductDto productDto) {
        this.productDAO.edit(this.productMapper.toEntity(productDto));
    }

    @Override
    @Transactional
    public ProductDto getById(int id) {
        return this.productMapper.toDto(this.productDAO.getById(id));
    }
}
