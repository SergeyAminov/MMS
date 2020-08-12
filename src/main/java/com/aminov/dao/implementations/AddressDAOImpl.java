package com.aminov.dao.implementations;

import com.aminov.dao.interfaces.AddressDAO;
import com.aminov.model.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressDAOImpl implements AddressDAO<Address> {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Address> allItems() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select address from Address address", Address.class).list();
    }

    @Override
    public void add(Address address) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(address);
    }

    @Override
    public void delete(Address address) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(address);
    }

    @Override
    public void edit(Address address) {
        Session session = sessionFactory.getCurrentSession();
        session.update(address);
    }

    @Override
    public Address getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Address.class, id);
    }

    @Override
    public List<Integer> getAddressIdListByUserId(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session
                .getSession()
                .createQuery("select address.id from Address address where address.user.id=:id", Integer.class)
                .setParameter("id", id)
                .list();
    }
}
