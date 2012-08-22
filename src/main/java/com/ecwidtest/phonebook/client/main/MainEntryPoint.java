package com.ecwidtest.phonebook.client.main;

import com.ecwidtest.phonebook.client.PhoneBookPlace;
import com.ecwidtest.phonebook.client.main.mvp.AppActivityMapper;
import com.ecwidtest.phonebook.client.main.mvp.AppPlaceHistoryMapper;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;


/**
 * @author d.sapaev
 */
public class MainEntryPoint implements EntryPoint {

  private SimplePanel appWidget = new SimplePanel();
  private Place defaultPlace = new PhoneBookPlace();

  public void onModuleLoad() {
    ClientFactory clientFactory = GWT.create(ClientFactory.class);
    EventBus eventBus = clientFactory.getEventBus();
    PlaceController placeController = clientFactory.getPlaceController();
    ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
    ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
    activityManager.setDisplay(appWidget);
    AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
    PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
    historyHandler.register(placeController, eventBus, defaultPlace);
    RootPanel.get().add(appWidget);
    historyHandler.handleCurrentHistory();
  }
}
