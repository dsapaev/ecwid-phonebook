package com.ecwidtest.phonebook.client;

import com.ecwidtest.phonebook.client.main.ClientFactory;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * @author d.sapaev
 */
public class PhoneBookActivity extends AbstractActivity implements PhoneBookView.Presenter {
  private ClientFactory clientFactory;

  public PhoneBookActivity(PhoneBookPlace place, ClientFactory clientFactory) {
    this.clientFactory = clientFactory;
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    PhoneBookView view = clientFactory.getPhoneBookView();
    view.setPresenter(this);
    panel.setWidget(view.asWidget());
  }

  @Override
  public void goTo(Place place) {
    clientFactory.getPlaceController().goTo(place);
  }
}
