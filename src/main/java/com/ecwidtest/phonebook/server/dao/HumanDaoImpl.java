package com.ecwidtest.phonebook.server.dao;

import com.ecwidtest.core.server.Hibernate4DaoSupport;
import com.ecwidtest.phonebook.server.bean.Human;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author d.sapaev
 */
@Repository
public class HumanDaoImpl extends Hibernate4DaoSupport implements HumanDao {

    @Transactional
    @Override
    public Long add(Human human) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(human);
        return human.getId();
    }

    @Transactional
    @Override
    public Human delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        Human toDelete = (Human) session.get(Human.class, id);

        if(toDelete!=null)
            session.delete(toDelete);

        return toDelete;
    }
}
