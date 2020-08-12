package com.aminov.mapper;

import com.aminov.dto.DeliveryStatusDto;
import com.aminov.model.DeliveryStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryStatusMapper {
    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DeliveryStatus toEntity(DeliveryStatusDto deliveryStatusDto){
        return modelMapper.map(deliveryStatusDto, DeliveryStatus.class);
    }

    public DeliveryStatusDto toDto(DeliveryStatus deliveryStatus){
        return modelMapper.map(deliveryStatus, DeliveryStatusDto.class);
    }
}
