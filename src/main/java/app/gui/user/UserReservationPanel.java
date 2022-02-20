package app.gui.user;

import app.business.dao.reservation.ReservationJpaDao;
import app.model.Guest;
import app.model.Reservation;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;

/**
 *
 * @author Harun
 */
public class UserReservationPanel extends VBox {

    private final TableView<Reservation> reservationTableView;

    private final Label fromDateLabel = new Label("From Date :");
    private final Label toDateLabel = new Label("To Date :");
    private final Label registerGuestLabel = new Label("Register Guest");
    private final Label nameSurnameLabel = new Label("Guest :");
    private final Label addressLabel = new Label("Address :");
    private final Label documentLabel = new Label("Passport/ID");

    private TextField guestNameTextField = new TextField();
    private TextField gusetSurnameTextField = new TextField();
    private TextField streetTextField = new TextField();
    private TextField streeTNumberTextField = new TextField();
    private TextField townTextField = new TextField();
    private TextField documentTextField = new TextField();
    private TextField countryTextField = new TextField();
    private final TextField toDaTextField = new TextField();
    private final DatePicker tableDatePicker = new DatePicker();

    GridPane gridPane = new GridPane();

    public UserReservationPanel() {
        setWidth(USE_PREF_SIZE);
        this.reservationTableView = new TableView<>();
        loadReservationTableView();
    }

    private void loadReservationTableView() {
        reservationTableView.setEditable(true);
        Callback<TableColumn<Reservation, Date>, TableCell<Reservation, Date>> dateCellFactory
                = (TableColumn<Reservation, Date> param) -> new DateEditingCell();

        TableColumn<Reservation, Guest> guestNameColumn = new TableColumn<>("Guest name :");
        guestNameColumn.setCellValueFactory(new PropertyValueFactory<>("idGuest"));
        guestNameColumn.setMinWidth(70);

        TableColumn<Reservation, Date> fromDateColumn = new TableColumn<>("From date:");
        fromDateColumn.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
        fromDateColumn.setMinWidth(70);
        fromDateColumn.setCellFactory(dateCellFactory);

        TableColumn<Reservation, Date> toDateColumn = new TableColumn<>("To date:");
        toDateColumn.setCellValueFactory(new PropertyValueFactory<>("toDate"));
        toDateColumn.setMinWidth(70);
        toDateColumn.setCellFactory(dateCellFactory);
        toDateColumn.setOnEditCommit(this::onEditCommit);

        TableColumn<Reservation, Date> checkInColumn = new TableColumn<>("Check in date:");
        checkInColumn.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
        checkInColumn.setMinWidth(70);

        TableColumn<Reservation, Date> checkOutColumn = new TableColumn<>("Check out date:");
        checkOutColumn.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
        checkOutColumn.setMinWidth(70);

        reservationTableView.getColumns().addAll(guestNameColumn, fromDateColumn, toDateColumn, checkInColumn, checkOutColumn);
        loadTableItems();
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(5));
        hBox.setSpacing(7);
        registerGuestLabel.setPadding(new Insets(5));
        registerGuestLabel.setFont(new Font(14));
        gridPane.setHgap(5);
        gridPane.setVgap(3);
        hBox.getChildren().addAll(fromDateHBox(), toDateHBox(), addGuestHBox());
        getChildren().addAll(registerGuestLabel, hBox, reservationTableView);
    }

    private void loadTableItems() {
        ObservableList<Reservation> reservationObservableList = FXCollections.observableArrayList();
        reservationObservableList.addAll(new ReservationJpaDao().getAll());
        reservationTableView.setItems(reservationObservableList);

    }

    private HBox fromDateHBox() {
        HBox hBox = new HBox();
        //  hBox.setSpacing(7);
        DatePicker fromDatePicker = new DatePicker();
        fromDatePicker.setPromptText("Chose date...");
        EventHandler<ActionEvent> eventHandler = (t) -> {
            LocalDate fromLocalDate = fromDatePicker.getValue();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd");
            Instant instant = Instant.from(fromLocalDate.atStartOfDay(ZoneId.systemDefault()));
            Date date = Date.from(Instant.from(fromLocalDate.atStartOfDay(ZoneId.systemDefault())));
            // Date date = Date.from(fromLocalDate.atStartOfDay().toInstant(ZoneOffset.UTC));
            //date = simpleDateFormat.get2DigitYearStart();
            //fromDateLabel.setText("From date : " + date);
        };
        fromDatePicker.setOnAction(eventHandler);
        // hBox.getChildren().addAll(fromDateLabel, fromDatePicker);
        gridPane.add(fromDateLabel, 0, 0);
        gridPane.add(fromDatePicker, 1, 0);
        hBox.getChildren().add(gridPane);
        return hBox;
    }

    private HBox toDateHBox() {
        HBox hBox = new HBox();
        // hBox.setSpacing(7);
        DatePicker toDatePicker = new DatePicker();
        toDatePicker.setPromptText("Chose date ...");
        EventHandler<ActionEvent> eventHandler = (t) -> {
            LocalDate fromLocalDate = toDatePicker.getValue();
            Date date = Date.from(Instant.from(fromLocalDate.atStartOfDay(ZoneId.systemDefault())));
            //fromDateLabel.setText("From date : " + date);
        };
        toDatePicker.setOnAction(eventHandler);
        //hBox.getChildren().addAll(toDateLabel, toDatePicker);
        gridPane.add(toDateLabel, 2, 0);
        gridPane.add(toDatePicker, 3, 0);
        hBox.getChildren().add(gridPane);
        return hBox;
    }

    private HBox addGuestHBox() {
        HBox hBox = new HBox();
        guestNameTextField.setPromptText("Enter guest name...");
        gusetSurnameTextField.setPromptText("Enter guest surname...");
        streetTextField.setPromptText("Street...");
        streeTNumberTextField.setPromptText("Street number...");
        townTextField.setPromptText("Town...");
        countryTextField.setPromptText("Country...");
        gridPane.add(nameSurnameLabel, 0, 1);
        gridPane.add(guestNameTextField, 1, 1);
        gridPane.add(gusetSurnameTextField, 3, 1);
        gridPane.add(addressLabel, 0, 2);
        gridPane.add(streetTextField, 1, 2);
        gridPane.add(streeTNumberTextField, 3, 2);
        gridPane.add(townTextField, 4, 2);
        gridPane.add(countryTextField, 5, 2);
        hBox.getChildren().add(gridPane);

        return hBox;
    }

    private void onEditCommit(TableColumn.CellEditEvent<Reservation, Date> editEvent) {
        ((Reservation) editEvent.getTableView().getItems()
                .get(editEvent.getTablePosition().getRow()))
                .setToDate(editEvent.getNewValue());
        ReservationJpaDao reservationJpaDao = new ReservationJpaDao();
        Reservation reservation = reservationJpaDao.get(editEvent.getRowValue().getId());
        reservation.setToDate(editEvent.getNewValue());
        if (reservation == null) {
            reservationJpaDao.save(reservation);
        } else {
            reservationJpaDao.update(reservation);
        }
    }
}
