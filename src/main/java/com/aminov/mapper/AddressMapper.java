package com.aminov.mapper;

import com.aminov.dto.AddressDto;
import com.aminov.model.Address;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Address toEntity(AddressDto addressDto){
        return modelMapper.map(addressDto, Address.class);
    }

    public AddressDto toDto(Address address){
        return modelMapper.map(address, AddressDto.class);
    }
}
