package com.aminov.dao;

import com.aminov.model.Parameters;

public interface ParametersDAO {
    void add(Parameters parameters);
    void delete(Parameters parameters);
    void edit(Parameters parameters);
    Parameters getById(int id);
}
