package com.aminov.mapper;

import com.aminov.dto.PaymentStatusDto;
import com.aminov.model.PaymentStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentStatusMapper {
    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PaymentStatus toEntity(PaymentStatusDto paymentStatusDto){
        return modelMapper.map(paymentStatusDto, PaymentStatus.class);
    }

    public PaymentStatusDto toDto(PaymentStatus paymentStatus){
        return modelMapper.map(paymentStatus, PaymentStatusDto.class);
    }
}
