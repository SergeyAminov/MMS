package com.aminov.service.interfaces;

import java.util.Map;

public interface DeliveryStatusService<T> extends InterfaceService<T> {
    Map<Integer, String> getIdTitleMap();
}
