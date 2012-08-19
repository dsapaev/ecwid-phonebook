package com.ecwidtest.phonebook.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("phoneList.rpc")
public interface PhoneBookService extends RemoteService {
  String getMessage(String msg);
}
