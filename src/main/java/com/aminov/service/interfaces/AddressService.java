package com.aminov.service.interfaces;

import java.util.Map;

public interface AddressService<T> extends InterfaceService<T> {
    public Map<Integer, String> getIdTitleMap();
    public Map<Integer, String> getIdTitleMapByUserId(int userId);
}
