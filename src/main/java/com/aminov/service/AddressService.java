package com.aminov.service;

import java.util.List;

public interface AddressService<T> extends InterfaceService<T> {
    public List<Integer> getAddressIdListByUserId(int id);
}
