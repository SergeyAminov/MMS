package com.aminov.service.interfaces;

import java.util.Map;

public interface CategoryService<T> extends InterfaceService<T> {
    Map<Integer, String> getCategoryIdTitleMap();
}