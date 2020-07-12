package com.aminov.dao;

import com.aminov.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Product> allProducts() {
        Session session = sessionFactory.getCurrentSession();

        /* Unsafe conversion has to be corrected here! */
        return session.createQuery("from Product ").list();
    }
}
