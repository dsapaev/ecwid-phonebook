package com.ecwidtest.phonebook.client;

import com.ecwidtest.phonebook.common.bean.PhoneNumberDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

@RemoteServiceRelativePath("phoneList.rpc")
public interface PhoneBookService extends RemoteService {
  String getMessage(String msg);

   /**
    *
    * @param toDelete
    */
  void deleteNumbers(List<PhoneNumberDTO> toDelete);

   /**
    *
    * @param number
    * @return
    */
  Long addNumber(PhoneNumberDTO number);

   /**
    *
    * @param number
    */
  void editNumber(PhoneNumberDTO number);
}
