package com.aminov.dao;

import com.aminov.model.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Category> allCategories() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select category from Category category", Category.class).list();
    }

    @Override
    public void add(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(category);
    }

    @Override
    public void delete(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(category);
    }

    @Override
    public void edit(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.update(category);
    }

    @Override
    public Category getById(String category) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Category.class, category);
    }

}
