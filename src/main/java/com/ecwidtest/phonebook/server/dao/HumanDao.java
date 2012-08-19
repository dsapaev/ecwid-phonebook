package com.ecwidtest.phonebook.server.dao;

import com.ecwidtest.phonebook.server.bean.Human;

/**
 * @author d.sapaev
 */
public interface HumanDao {

  Human add(Human arg);

  Human delete(long id);
}
