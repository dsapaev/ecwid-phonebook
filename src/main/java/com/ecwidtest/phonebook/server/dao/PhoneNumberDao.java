package com.ecwidtest.phonebook.server.dao;


import com.ecwidtest.phonebook.server.bean.PhoneNumber;

import java.util.List;

/**
 * @author d.sapaev
 */
public interface PhoneNumberDao {

    List<PhoneNumber> loadList();

    Long add(PhoneNumber phoneNumber);

    PhoneNumber delete(Long id);
}
