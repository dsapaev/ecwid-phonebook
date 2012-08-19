package com.ecwidtest.phonebook.server.dao;

import com.ecwidtest.core.server.Hibernate4DaoSupport;
import com.ecwidtest.phonebook.server.bean.PhoneNumber;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

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
            try {
                if (session != null) {
                    session.close();
                }
            } catch (HibernateException ex) {
                logger.error(ex.toString(), ex);
            }
        }
    }
}
