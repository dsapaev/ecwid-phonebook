package com.ecwidtest.phonebook.client.main;

import com.ecwidtest.phonebook.client.PhoneBookView;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;



/**
 * @author d.sapaev
 */
public interface ClientFactory {

  EventBus getEventBus();

  PlaceController getPlaceController();

  PhoneBookView getPhoneBookView();
}
