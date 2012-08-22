package com.ecwidtest.phonebook.client.main.mvp;


import com.ecwidtest.phonebook.client.PhoneBookActivity;
import com.ecwidtest.phonebook.client.PhoneBookPlace;
import com.ecwidtest.phonebook.client.main.ClientFactory;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

/**
 * @author d.sapaev
 */
public class AppActivityMapper implements ActivityMapper {
  private ClientFactory clientFactory;

  public AppActivityMapper(ClientFactory clientFactory) {
    super();
    this.clientFactory = clientFactory;
  }

  @Override
  public Activity getActivity(Place place) {

    if (place instanceof PhoneBookPlace)
      return new PhoneBookActivity((PhoneBookPlace) place, clientFactory);
    
    return null;
  }

}
