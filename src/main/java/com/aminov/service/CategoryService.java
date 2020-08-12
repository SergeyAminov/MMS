package com.aminov.service;

import java.util.Map;

public interface CategoryService<T> extends InterfaceService<T> {
    public Map<Integer, String> getCategoryIdTitleMap();
}