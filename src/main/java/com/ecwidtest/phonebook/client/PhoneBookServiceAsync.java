package com.ecwidtest.phonebook.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PhoneBookServiceAsync {
  void getMessage(String msg, AsyncCallback<String> async);
}
