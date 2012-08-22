package com.ecwidtest.phonebook.client;

import com.ecwidtest.phonebook.common.bean.PhoneNumberDTO;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.Place;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SelectionModel;


import java.util.Comparator;
import java.util.List;

/**
 * @author d.sapaev
 */
public class PhoneBookViewImpl extends Composite implements PhoneBookView {
  private Presenter presenter;
  private DockPanel viewPanel = new DockPanel();

  private PhoneBookServiceAsync service;

  public PhoneBookViewImpl() {
    service = GWT.create(PhoneBookService.class);

    viewPanel.setWidth("100%");
    Widget header = createHeader();
    viewPanel.add(header, DockPanel.CENTER);
    viewPanel.setCellHorizontalAlignment(header, HasHorizontalAlignment.ALIGN_RIGHT);

    viewPanel.add(createListPanel(), DockPanel.LINE_START);

    initWidget(viewPanel);
  }

  @Override
  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  protected Widget createHeader() {
    VerticalPanel header = new VerticalPanel();
    //todo использовать Messages
    header.add(new Label("Список телефонных номеров"));

    return header;
  }

  private PushButton createMenuItem(ImageResource upImgResource, ImageResource downImgResource, final Place place) {
    Image upImage = new Image(upImgResource);
    Image downImage = new Image(downImgResource);
    PushButton btnTariff = new PushButton(upImage, new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        if (place != null)
          presenter.goTo(place);
      }
    });
    return btnTariff;
  }

  
  protected Widget createListPanel() {
    DockPanel listPanel = new DockPanel();

    listPanel.add(createPhoneBookTable(), DockPanel.CENTER);

    return listPanel;
  }

  private CellTable<PhoneNumberDTO> createPhoneBookTable() {
    CellTable<PhoneNumberDTO> cellTable = new CellTable<PhoneNumberDTO>();
    cellTable.setWidth("80%", true);

    AsyncDataProvider<PhoneNumberDTO> dataProvider = new AsyncDataProvider<PhoneNumberDTO>() {
      @Override
      protected void onRangeChanged(HasData<PhoneNumberDTO> display) {
        final Range range = display.getVisibleRange();
        service.loadData(range, new AsyncCallback<List<PhoneNumberDTO>>() {
          @Override
          public void onFailure(Throwable caught) {
            DialogBox dlg = new DialogBox(true, true);
            dlg.setText("При получении данных произошла ошибка! " + caught.toString());
            dlg.show();
          }

          @Override
          public void onSuccess(List<PhoneNumberDTO> result) {
            updateRowData(range.getStart(), result);
          }
        });
      }
    };

    ColumnSortEvent.AsyncHandler sortHandler = new ColumnSortEvent.AsyncHandler(cellTable);
    cellTable.addColumnSortHandler(sortHandler);

    // Create a Pager to control the table.
    SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
    SimplePager pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, false, 0, true);
    pager.setDisplay(cellTable);

    ProvidesKey<PhoneNumberDTO> keyProvider = new ProvidesKey<PhoneNumberDTO>() {
      @Override
      public Object getKey(PhoneNumberDTO item) {
        return item != null ? item.getId() : null;
      }
    };

    // Add a selection model so we can select cells.
    final SelectionModel<PhoneNumberDTO> selectionModel = new MultiSelectionModel<PhoneNumberDTO> (keyProvider);
    cellTable.setSelectionModel(selectionModel, DefaultSelectionEventManager.<PhoneNumberDTO>createCheckboxManager());

    // Initialize the columns.
    initTableColumns(cellTable, selectionModel, sortHandler);

    // Add the CellList to the adapter in the database.
    dataProvider.addDataDisplay(cellTable);

    return cellTable;
  }

  private void initTableColumns(CellTable<PhoneNumberDTO> cellTable, final SelectionModel<PhoneNumberDTO> selectionModel,
                                ColumnSortEvent.AsyncHandler sortHandler) {
    Column<PhoneNumberDTO, Boolean> checkColumn = new Column<PhoneNumberDTO, Boolean> (
        new CheckboxCell(true, false)) {
      @Override
      public Boolean getValue(PhoneNumberDTO object) {
        // Get the value from the selection model.
        return selectionModel.isSelected(object);
      }
    };
    cellTable.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
    cellTable.setColumnWidth(checkColumn, 40, Style.Unit.PX);

    // region
    Column<PhoneNumberDTO, String> firstNameColumn = new Column<PhoneNumberDTO, String>(new TextCell()) {
      @Override
      public String getValue(PhoneNumberDTO object) {
        return object.getOwner().getFirstName();
      }
    };
    //todo реализовать сортировку
    firstNameColumn.setSortable(false);
    cellTable.addColumn(firstNameColumn, "Имя");
    cellTable.setColumnWidth(firstNameColumn, 20, Style.Unit.PCT);

    // transporter
    Column<PhoneNumberDTO, String> lastNameColumn = new Column<PhoneNumberDTO, String>(new TextCell()) {
      @Override
      public String getValue(PhoneNumberDTO object) {
        return object.getOwner().getLastName();
      }
    };
    //todo реализовать сортировку
    lastNameColumn.setSortable(false);
    cellTable.addColumn(lastNameColumn, "Фамилия");
    cellTable.setColumnWidth(lastNameColumn, 20, Style.Unit.PCT);

    // transportType
    Column<PhoneNumberDTO, String> numberColumn = new Column<PhoneNumberDTO, String>(new TextCell()) {
      @Override
      public String getValue(PhoneNumberDTO object) {
        return object.getPhoneNum();
      }
    };
    //todo реализовать сортировку
    numberColumn.setSortable(false);
    cellTable.addColumn(numberColumn, "Номер");
    cellTable.setColumnWidth(numberColumn, 20, Style.Unit.PCT);
  }
}
