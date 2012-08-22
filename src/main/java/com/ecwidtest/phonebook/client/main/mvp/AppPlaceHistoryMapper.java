package com.ecwidtest.phonebook.client.main.mvp;


import com.ecwidtest.phonebook.client.PhoneBookPlace;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;


@WithTokenizers({ PhoneBookPlace.Tokenizer.class})
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}
