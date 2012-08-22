package com.ecwidtest.phonebook.client;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author d.sapaev
 */
public interface PhoneBookView extends IsWidget {
  void setPresenter(Presenter presenter);

  public interface Presenter {
    void goTo(Place place);
  }
}
