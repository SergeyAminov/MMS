package com.aminov.service;

import com.aminov.model.Parameters;

public interface ParametersService {
    void add(Parameters parameters);
    void delete(Parameters parameters);
    void edit(Parameters parameters);
    Parameters getById(int id);
}
