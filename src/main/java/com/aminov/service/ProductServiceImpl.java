package com.aminov.service;

import com.aminov.dao.ProductDAO;
import com.aminov.dto.ProductDto;
import com.aminov.mapper.ExProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;
    private ExProductMapper exProductMapper;

    @Autowired
    public void setProductDAO(ProductDAO productDAO){
        this.productDAO = productDAO;
    }

    @Autowired
    public void setExProductMapper(ExProductMapper exProductMapper){
        this.exProductMapper = exProductMapper;
    }

    @Override
    @Transactional
    public List<ProductDto> allProducts() {
        return this.productDAO
                .allProducts()
                .stream()
                .map(this.exProductMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void add(ProductDto productDto) {
        this.productDAO.add(this.exProductMapper.toEntity(productDto));
    }

    @Override
    @Transactional
    public void delete(ProductDto productDto) {
        this.productDAO.delete(this.exProductMapper.toEntity(productDto));
    }

    @Override
    @Transactional
    public void edit(ProductDto productDto) {
        this.productDAO.edit(this.exProductMapper.toEntity(productDto));
    }

    @Override
    @Transactional
    public ProductDto getById(int id) {
        return this.exProductMapper.toDto(this.productDAO.getById(id));
    }
}
