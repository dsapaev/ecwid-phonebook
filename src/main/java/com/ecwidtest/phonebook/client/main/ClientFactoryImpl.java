package com.ecwidtest.phonebook.client.main;

import com.ecwidtest.phonebook.client.PhoneBookView;
import com.ecwidtest.phonebook.client.PhoneBookViewImpl;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

/**
 * @author d.sapaev
 */
public class ClientFactoryImpl implements ClientFactory {
	private final EventBus eventBus = new SimpleEventBus();
	private final PlaceController placeController = new PlaceController(
			eventBus);
	private final PhoneBookView phoneBookView = new PhoneBookViewImpl();

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public PhoneBookView getPhoneBookView() {
		return phoneBookView;
	}

}
