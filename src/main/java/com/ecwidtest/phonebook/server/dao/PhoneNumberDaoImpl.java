package com.ecwidtest.phonebook.server.dao;

import com.ecwidtest.core.server.Hibernate4DaoSupport;
import com.ecwidtest.phonebook.server.bean.PhoneNumber;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
@Repository
public class PhoneNumberDaoImpl extends Hibernate4DaoSupport implements PhoneNumberDao {

    @Transactional
    @Override
    public List<PhoneNumber> loadList() {
        Session session = sessionFactory.getCurrentSession();
        return (List<PhoneNumber>)session.createQuery("from PhoneNumber").list();
    }

    @Transactional
    @Override
    public Long add(PhoneNumber phoneNumber) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(phoneNumber);
        return phoneNumber.getId();
    }

    @Transactional
    @Override
    public PhoneNumber delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        PhoneNumber toDelete = (PhoneNumber) session.get(PhoneNumber.class, id);

        if(toDelete!=null)
            session.delete(toDelete);

        return toDelete;
    }
}
