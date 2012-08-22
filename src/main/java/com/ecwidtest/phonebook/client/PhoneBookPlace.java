package com.ecwidtest.phonebook.client;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author d.sapaev
 */
public class PhoneBookPlace extends Place {

  public PhoneBookPlace() {
  }

  public static class Tokenizer implements PlaceTokenizer<PhoneBookPlace> {
    @Override
    public PhoneBookPlace getPlace(String token) {
      return new PhoneBookPlace();
    }

    @Override
    public String getToken(PhoneBookPlace place) {
      return null;
    }
  }
}
