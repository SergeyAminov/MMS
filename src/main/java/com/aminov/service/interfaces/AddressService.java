package com.aminov.service.interfaces;

import java.util.List;

public interface AddressService<T> extends InterfaceService<T> {
    public List<Integer> getAddressIdListByUserId(int id);
}
