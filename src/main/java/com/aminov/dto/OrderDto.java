package com.aminov.dto;

import java.util.List;

public class OrderDto {
    private int id;
    private UserDto userDto;
    private AddressDto addressDto;
    private List<ProductDto> productDtoList;
    private PaymentMethodDto paymentMethodDto;
    private DeliveryMethodDto deliveryMethodDto;
    private PaymentStatusDto paymentStatusDto;
    private DeliveryStatusDto deliveryStatusDto;

}
