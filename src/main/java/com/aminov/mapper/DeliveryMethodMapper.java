package com.aminov.mapper;

import com.aminov.dto.DeliveryMethodDto;
import com.aminov.model.DeliveryMethod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryMethodMapper {
    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DeliveryMethod toEntity(DeliveryMethodDto deliveryMethodDto){
        return modelMapper.map(deliveryMethodDto, DeliveryMethod.class);
    }

    public DeliveryMethodDto toDto(DeliveryMethod deliveryMethod){
        return modelMapper.map(deliveryMethod, DeliveryMethodDto.class);
    }
}
