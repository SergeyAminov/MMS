package com.aminov.service;

import com.aminov.dao.ProductDAO;
import com.aminov.dto.ProductDto;
import com.aminov.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;
    private ProductMapper productMapper;

    @Autowired
    public void setProductDAO(ProductDAO productDAO){
        this.productDAO = productDAO;
    }

    @Autowired
    public void setProductMapper(ProductMapper productMapper){
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public List<ProductDto> allProducts() {
        return this.productDAO
                .allProducts()
                .stream()
                .map(this.productMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void add(ProductDto productDto) {
        this.productDAO.add(this.productMapper.convertToEntity(productDto));
    }

    @Override
    @Transactional
    public void delete(ProductDto productDto) {
        this.productDAO.delete(this.productMapper.convertToEntity(productDto));
    }

    @Override
    @Transactional
    public void edit(ProductDto productDto) {
        this.productDAO.edit(this.productMapper.convertToEntity(productDto));
    }

    @Override
    @Transactional
    public ProductDto getById(int id) {
        return this.productMapper.convertToDto(this.productDAO.getById(id));
    }
}
