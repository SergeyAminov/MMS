package com.aminov.dao.interfaces;

import java.util.List;

public interface AddressDAO<T> extends InterfaceDAO<T> {
    public List<Integer> getAddressIdListByUserId(int id);
}
