package com.aminov.service;

import com.aminov.dao.ParametersDAO;
import com.aminov.model.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ParametersServiceImpl implements ParametersService<Parameters>{
    private ParametersDAO<Parameters> parametersDAO;

    @Autowired
    public void setParametersDAO(ParametersDAO<Parameters> parametersDAO){
        this.parametersDAO = parametersDAO;
    }

    @Override
    public List<Parameters> allItems() {
        return this.parametersDAO.allItems();
    }

    @Transactional
    @Override
    public void add(Parameters parameters) {
        this.parametersDAO.add(parameters);
    }

    @Transactional
    @Override
    public void delete(Parameters parameters) {
        this.parametersDAO.delete(parameters);
    }

    @Transactional
    @Override
    public void edit(Parameters parameters) {
        this.parametersDAO.edit(parameters);
    }

    @Transactional
    @Override
    public Parameters getById(int id) {
        return this.parametersDAO.getById(id);
    }
}
