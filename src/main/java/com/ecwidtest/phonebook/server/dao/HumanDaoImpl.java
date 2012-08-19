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
    public Long add(Human human) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.saveOrUpdate(human);
            return human.getId();
        } finally {
            closeSession(session);
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
            closeSession(session);
        }
    }
}
