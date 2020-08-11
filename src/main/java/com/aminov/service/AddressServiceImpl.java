package com.aminov.service;

import com.aminov.dao.AddressDAO;
import com.aminov.dto.AddressDto;
import com.aminov.mapper.AddressMapper;
import com.aminov.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService<AddressDto> {

    private AddressDAO<Address> addressDAO;
    private AddressMapper addressMapper;

    @Autowired
    public void setAddressDAO(AddressDAO<Address> addressDAO) {
        this.addressDAO = addressDAO;
    }

    @Autowired
    public void setAddressMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Transactional
    @Override
    public List<AddressDto> allItems() {
        return this.addressDAO
                .allItems()
                .stream()
                .map(this.addressMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void add(AddressDto addressDto) {
        this.addressDAO.add(this.addressMapper.toEntity(addressDto));
    }

    @Transactional
    @Override
    public void delete(AddressDto addressDto) {
        this.addressDAO.delete(this.addressMapper.toEntity(addressDto));
    }

    @Transactional
    @Override
    public void edit(AddressDto addressDto) {
        this.addressDAO.edit(this.addressMapper.toEntity(addressDto));
    }

    @Transactional
    @Override
    public AddressDto getById(int id) {
        return this.addressMapper.toDto(this.addressDAO.getById(id));
    }
}
