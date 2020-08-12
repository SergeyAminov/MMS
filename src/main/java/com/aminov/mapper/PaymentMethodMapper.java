package com.aminov.mapper;

import com.aminov.dto.PaymentMethodDto;
import com.aminov.model.PaymentMethod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentMethodMapper {
    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PaymentMethod toEntity(PaymentMethodDto paymentMethodDto){
        return modelMapper.map(paymentMethodDto, PaymentMethod.class);
    }

    public PaymentMethodDto toDto(PaymentMethod paymentMethod){
        return modelMapper.map(paymentMethod, PaymentMethodDto.class);
    }
}
