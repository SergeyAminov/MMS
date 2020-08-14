package com.aminov.service.implementations;

import com.aminov.dao.interfaces.DeliveryStatusDAO;
import com.aminov.dto.DeliveryStatusDto;
import com.aminov.mapper.DeliveryStatusMapper;
import com.aminov.model.DeliveryStatus;
import com.aminov.service.interfaces.DeliveryStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DeliveryStatusServiceImpl implements DeliveryStatusService<DeliveryStatusDto> {
    private DeliveryStatusMapper deliveryStatusMapper;
    private DeliveryStatusDAO<DeliveryStatus> deliveryStatusDAO;

    @Autowired
    public void setDeliveryStatusMapper(DeliveryStatusMapper deliveryStatusMapper) {
        this.deliveryStatusMapper = deliveryStatusMapper;
    }

    @Autowired
    public void setDeliveryStatusDAO(DeliveryStatusDAO<DeliveryStatus> deliveryStatusDAO) {
        this.deliveryStatusDAO = deliveryStatusDAO;
    }

    @Transactional
    @Override
    public Map<Integer, String> getIdTitleMap() {
        Map<Integer, String> map = new HashMap<>();
        for (DeliveryStatusDto deliveryStatusDto : this.allItems())
            map.put(deliveryStatusDto.getId(), deliveryStatusDto.getTitle());
        return map;
    }

    @Transactional
    @Override
    public List<DeliveryStatusDto> allItems() {
        return this.deliveryStatusDAO
                .allItems()
                .stream()
                .map(this.deliveryStatusMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void add(DeliveryStatusDto deliveryStatusDto) {
        this.deliveryStatusDAO.add(this.deliveryStatusMapper.toEntity(deliveryStatusDto));
    }

    @Transactional
    @Override
    public void delete(DeliveryStatusDto deliveryStatusDto) {
        this.deliveryStatusDAO.delete(this.deliveryStatusMapper.toEntity(deliveryStatusDto));
    }

    @Transactional
    @Override
    public void edit(DeliveryStatusDto deliveryStatusDto) {
        this.deliveryStatusDAO.edit(this.deliveryStatusMapper.toEntity(deliveryStatusDto));
    }

    @Transactional
    @Override
    public DeliveryStatusDto getById(int id) {
        return this.deliveryStatusMapper.toDto(this.deliveryStatusDAO.getById(id));
    }
}
