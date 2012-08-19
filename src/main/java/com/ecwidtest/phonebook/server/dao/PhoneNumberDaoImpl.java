package com.ecwidtest.phonebook.server.dao;

import com.ecwidtest.core.server.Hibernate4DaoSupport;
import com.ecwidtest.phonebook.server.bean.PhoneNumber;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public class PhoneNumberDaoImpl extends Hibernate4DaoSupport implements PhoneNumberDao {
    @Override
    public List<PhoneNumber> loadList() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return (List<PhoneNumber>)session.createQuery("from PhoneNumber").list();
        } finally {
            closeSession(session);
        }
    }

    @Override
    public Long add(PhoneNumber phoneNumber) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.saveOrUpdate(phoneNumber);
            return phoneNumber.getId();
        } finally {
            closeSession(session);
        }
    }

    @Override
    public PhoneNumber delete(Long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            PhoneNumber toDelete = (PhoneNumber) session.get(PhoneNumber.class, id);

            if(toDelete!=null)
                session.delete(toDelete);

            return toDelete;
        } finally {
            closeSession(session);
        }
    }
}
