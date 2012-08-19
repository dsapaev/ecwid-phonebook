package com.ecwidtest.phonebook.server.dao;

import com.ecwidtest.phonebook.server.bean.Human;

/**
 * @author d.sapaev
 */
public interface HumanDao {

    /**
     *
     * @param human
     * @return
     */
  Long add(Human human);

    /**
     *
     * @param id
     * @return
     */
  Human delete(long id);
}
