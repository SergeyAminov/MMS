package com.aminov.service.implementations;

import com.aminov.dao.interfaces.PaymentStatusDAO;
import com.aminov.dto.PaymentStatusDto;
import com.aminov.mapper.PaymentStatusMapper;
import com.aminov.model.PaymentStatus;
import com.aminov.service.interfaces.PaymentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PaymentStatusServiceImpl implements PaymentStatusService<PaymentStatusDto> {
    private PaymentStatusMapper paymentStatusMapper;
    private PaymentStatusDAO<PaymentStatus> paymentStatusDAO;

    @Autowired
    public void setPaymentStatusMapper(PaymentStatusMapper paymentStatusMapper) {
        this.paymentStatusMapper = paymentStatusMapper;
    }

    @Autowired
    public void setPaymentStatusDAO(PaymentStatusDAO<PaymentStatus> paymentStatusDAO) {
        this.paymentStatusDAO = paymentStatusDAO;
    }

    @Transactional
    @Override
    public Map<Integer, String> getIdTitleMap() {
        Map<Integer, String> map = new HashMap<>();
        for (PaymentStatusDto paymentStatusDto : this.allItems())
            map.put(paymentStatusDto.getId(), paymentStatusDto.getTitle());
        return map;
    }

    @Transactional
    @Override
    public List<PaymentStatusDto> allItems() {
        return this.paymentStatusDAO
                .allItems()
                .stream()
                .map(this.paymentStatusMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void add(PaymentStatusDto paymentStatusDto) {
        this.paymentStatusDAO.add(this.paymentStatusMapper.toEntity(paymentStatusDto));
    }

    @Transactional
    @Override
    public void delete(PaymentStatusDto paymentStatusDto) {
        this.paymentStatusDAO.delete(this.paymentStatusMapper.toEntity(paymentStatusDto));
    }

    @Transactional
    @Override
    public void edit(PaymentStatusDto paymentStatusDto) {
        this.paymentStatusDAO.delete(this.paymentStatusMapper.toEntity(paymentStatusDto));
    }

    @Transactional
    @Override
    public PaymentStatusDto getById(int id) {
        return this.paymentStatusMapper.toDto(this.paymentStatusDAO.getById(id));
    }
}
