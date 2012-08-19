package com.ecwidtest.phonebook.server.dao;

import com.ecwidtest.core.server.Hibernate4DaoSupport;
import com.ecwidtest.phonebook.server.bean.Human;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author d.sapaev
 */
@Repository
public class HumanDaoImpl extends Hibernate4DaoSupport implements HumanDao {

  @Override
  protected void checkDaoConfig() throws IllegalArgumentException {
    //todo: implement
  }

    @Override
    public Human add(Human arg) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.saveOrUpdate(arg);
            return arg;
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

    @Override
    public Human delete(long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Human toDelete = (Human) session.get(Human.class, id);

            if(toDelete!=null)
                session.delete(toDelete);

            return toDelete;
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
