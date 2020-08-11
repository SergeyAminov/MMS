package com.aminov.dao;

import java.util.List;

public interface AddressDAO<T> extends InterfaceDAO<T> {
    public List<Integer> getAddressIdListByUserId(int id);
}
