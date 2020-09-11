package com.aminov.service.implementations;

import com.aminov.dao.interfaces.ProductDAO;
import com.aminov.dto.ProductDto;
import com.aminov.mapper.ProductMapper;
import com.aminov.model.Product;
import com.aminov.service.interfaces.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public List<ProductDto> getFilteredItems(Map<String, String> params) {
        List<ProductDto> filteredList = new ArrayList<>();
        for (ProductDto product : this.allItems()){
            if (params.get("min-price") != null){
                double minPrice = Double.parseDouble(params.get("min-price"));
                double maxPrice = Double.parseDouble(params.get("max-price"));
                if (!(product.getPrice() >= minPrice && product.getPrice() <= maxPrice)){
                    continue;
                }
            }
            if (params.get("min-weight") != null){
                double minWeight = Double.parseDouble(params.get("min-weight"));
                double maxWeight = Double.parseDouble(params.get("max-weight"));
                if (!(product.getWeight() >= minWeight && product.getWeight() <= maxWeight)){
                    continue;
                }
            }
            if (params.get("category") != null){
                String category = params.get("category");
                if (!(product.getCategoryId() == Integer.parseInt(category))){
                    continue;
                }
            }
            filteredList.add(product);
        }
        return filteredList;
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

    @Override
    @Transactional
    public String convertToJSON(ProductDto productDto){
        String jsonString;
        ObjectMapper mapper = new ObjectMapper();
        try {
            jsonString = mapper.writeValueAsString(productDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            jsonString = "Error with converting to JSON";
        }
        return jsonString;
    }

    @Override
    @Transactional
    public String convertToJSON(List<ProductDto> productDtoList){
        String jsonString;
        ObjectMapper mapper = new ObjectMapper();
        try {
            jsonString = mapper.writeValueAsString(productDtoList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            jsonString = "Error with converting to JSON";
        }
        return jsonString;
    }

}
