package com.aminov.service.implementations;

import com.aminov.dao.interfaces.DeliveryMethodDAO;
import com.aminov.dto.DeliveryMethodDto;
import com.aminov.mapper.DeliveryMethodMapper;
import com.aminov.model.DeliveryMethod;
import com.aminov.service.interfaces.DeliveryMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DeliveryMethodServiceImpl implements DeliveryMethodService<DeliveryMethodDto> {
    private DeliveryMethodMapper deliveryMethodMapper;
    private DeliveryMethodDAO<DeliveryMethod> deliveryMethodDAO;

    @Autowired
    public void setDeliveryMethodMapper(DeliveryMethodMapper deliveryMethodMapper) {
        this.deliveryMethodMapper = deliveryMethodMapper;
    }

    @Autowired
    public void setDeliveryMethodDAO(DeliveryMethodDAO<DeliveryMethod> deliveryMethodDAO) {
        this.deliveryMethodDAO = deliveryMethodDAO;
    }

    @Transactional
    @Override
    public Map<Integer, String> getIdTitleMap() {
        Map<Integer, String> map = new HashMap<>();
        for (DeliveryMethodDto deliveryMethodDto : this.allItems())
            map.put(deliveryMethodDto.getId(), deliveryMethodDto.getTitle());
        return map;
    }

    @Transactional
    @Override
    public List<DeliveryMethodDto> allItems() {
        return this.deliveryMethodDAO
                .allItems()
                .stream()
                .map(this.deliveryMethodMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void add(DeliveryMethodDto deliveryMethodDto) {
        this.deliveryMethodDAO.add(this.deliveryMethodMapper.toEntity(deliveryMethodDto));
    }

    @Transactional
    @Override
    public void delete(DeliveryMethodDto deliveryMethodDto) {
        this.deliveryMethodDAO.delete(this.deliveryMethodMapper.toEntity(deliveryMethodDto));
    }

    @Transactional
    @Override
    public void edit(DeliveryMethodDto deliveryMethodDto) {
        this.deliveryMethodDAO.edit(this.deliveryMethodMapper.toEntity(deliveryMethodDto));
    }

    @Transactional
    @Override
    public DeliveryMethodDto getById(int id) {
        return this.deliveryMethodMapper.toDto(this.deliveryMethodDAO.getById(id));
    }
}
