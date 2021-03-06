package com.ecwidtest.phonebook.client;

import com.ecwidtest.phonebook.common.bean.PhoneNumberDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.Range;

import java.util.List;

public interface PhoneBookServiceAsync {
  void getMessage(String msg, AsyncCallback<String> async);

    void deleteNumbers(List<PhoneNumberDTO> toDelete, AsyncCallback<Void> async);

    void addNumber(PhoneNumberDTO number, AsyncCallback<Long> async);

    void editNumber(PhoneNumberDTO number, AsyncCallback<Void> async);

	void loadData(Range range, AsyncCallback<List<PhoneNumberDTO>> callback);
}
