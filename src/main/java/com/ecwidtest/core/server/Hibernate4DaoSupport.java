package com.ecwidtest.core.server;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;

/**
 * @author d.sapaev
 */
public class Hibernate4DaoSupport<T> extends DaoSupport {
  protected SessionFactory sessionFactory;

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  @Autowired
  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  protected void checkDaoConfig() throws IllegalArgumentException {
    if (this.sessionFactory == null) {
      throw new IllegalArgumentException("'sessionFactory' is required");
    }
  }

  protected void closeSession(Session session){
      try {
          if (session != null) {
              session.close();
          }
      } catch (HibernateException ex) {
          logger.error(ex.toString(), ex);
      }
  }
}
