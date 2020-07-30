package com.aminov.mapper;

import com.aminov.dao.CategoryDAO;
import com.aminov.dto.ProductDto;
import com.aminov.model.Product;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements InitializingBean {

    private ModelMapper modelMapper;
    private CategoryDAO categoryDAO;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setCategoryDAO(CategoryDAO categoryDAO){
        this.categoryDAO = categoryDAO;
    }

    // Instead of @PostConstruct annotation
    @Override
    public void afterPropertiesSet() {
        this.modelMapper.createTypeMap(Product.class, ProductDto.class)
                .addMappings(m -> m.skip(ProductDto::setCategoryId)).setPostConverter(toDtoConverter());
        this.modelMapper.createTypeMap(ProductDto.class, Product.class)
                .addMappings(m -> m.skip(Product::setCategory)).setPostConverter(toEntityConverter());
    }

    public Converter<Product, ProductDto> toDtoConverter() {
        return context -> {
            Product source = context.getSource();
            ProductDto destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public Converter<ProductDto, Product> toEntityConverter() {
        return context -> {
            ProductDto source = context.getSource();
            Product destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    void mapSpecificFields(Product source, ProductDto destination) {
        destination.setCategoryId(source.getCategory().getId());
    }

    void mapSpecificFields(ProductDto source, Product destination) {
        destination.setCategory(categoryDAO.getById(source.getCategoryId()));
    }

    public ProductDto convertToDto(Product product) {
        return this.modelMapper.map(product, ProductDto.class);
    }

    public Product convertToEntity(ProductDto productDto){
        return this.modelMapper.map(productDto, Product.class);
    }

}
