package com.aminov.service.implementations;

import com.aminov.dao.interfaces.PaymentMethodDAO;
import com.aminov.dto.PaymentMethodDto;
import com.aminov.mapper.PaymentMethodMapper;
import com.aminov.model.PaymentMethod;
import com.aminov.service.interfaces.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService<PaymentMethodDto> {
    private PaymentMethodMapper paymentMethodMapper;
    private PaymentMethodDAO<PaymentMethod> paymentMethodDAO;

    @Autowired
    public void setPaymentMethodMapper(PaymentMethodMapper paymentMethodMapper) {
        this.paymentMethodMapper = paymentMethodMapper;
    }

    @Autowired
    public void setPaymentMethodDAO(PaymentMethodDAO<PaymentMethod> paymentMethodDAO) {
        this.paymentMethodDAO = paymentMethodDAO;
    }

    @Transactional
    @Override
    public Map<Integer, String> getIdTitleMap() {
        Map<Integer, String> map = new HashMap<>();
        for (PaymentMethodDto paymentMethodDto : this.allItems())
            map.put(paymentMethodDto.getId(), paymentMethodDto.getTitle());
        return map;
    }

    @Transactional
    @Override
    public List<PaymentMethodDto> allItems() {
        return this.paymentMethodDAO
                .allItems()
                .stream()
                .map(this.paymentMethodMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void add(PaymentMethodDto paymentMethodDto) {
        this.paymentMethodDAO.add(this.paymentMethodMapper.toEntity(paymentMethodDto));
    }

    @Transactional
    @Override
    public void delete(PaymentMethodDto paymentMethodDto) {
        this.paymentMethodDAO.delete(this.paymentMethodMapper.toEntity(paymentMethodDto));
    }

    @Transactional
    @Override
    public void edit(PaymentMethodDto paymentMethodDto) {
        this.paymentMethodDAO.edit(this.paymentMethodMapper.toEntity(paymentMethodDto));
    }

    @Transactional
    @Override
    public PaymentMethodDto getById(int id) {
        return this.paymentMethodMapper.toDto(this.paymentMethodDAO.getById(id));
    }
}
