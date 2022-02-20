/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.gui.admin.panel;

import app.business.dao.room.RoomJPAdao;
import app.constants.MessageDialog;
import app.model.Room;
import java.math.BigDecimal;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Harun
 */
public class AdminRoomPanel extends VBox {

    private final Label titleLabel = new Label("Administracija soba");
    private final TableView<Room> roomTableView = new TableView<>();
    private ObservableList<Room> rooms = null;

    private final TextField roomNumberTextField = new TextField();
    private final TextField bedsNumberTextField = new TextField();
    private final TextField roomPriceTextField = new TextField();

    private final Button addButton = new Button("Add room");
    private final Button deleteButton = new Button("Delete room");

    public AdminRoomPanel() {
        titleLabel.setFont(new Font("Arial", 20));
        setSpacing(5);
        setPadding(new Insets(10));

        List<Room> roomList = new RoomJPAdao().getAll();
        rooms = FXCollections.observableArrayList(roomList);
        roomTableView.setItems(rooms);

        TableColumn<Room, String> codeColumn = new TableColumn<>("Šifra sobe");
        codeColumn.setMinWidth(150);
        codeColumn.setCellValueFactory(new PropertyValueFactory<Room, String>("code"));

        TableColumn<Room, Integer> bedsColumn = new TableColumn<>("Broj kreveta");
        bedsColumn.setMinWidth(150);
        bedsColumn.setCellValueFactory(new PropertyValueFactory<>("beds"));

        TableColumn<Room, BigDecimal> priceColumn = new TableColumn<>("Cijena");
        priceColumn.setMinWidth(150);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        roomTableView.getColumns().addAll(codeColumn, bedsColumn, priceColumn);
        roomTableView.setItems(rooms);
        getChildren().addAll(titleLabel, roomTableView, getFormBox());
    }

    public HBox getFormBox() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10));
        roomNumberTextField.setPromptText("Room code...");
        bedsNumberTextField.setPromptText("Number of beds...");
        roomPriceTextField.setPromptText("Price per day...");
        addButton.setOnAction(this::onAddButtonClick);
        hBox.getChildren().addAll(roomNumberTextField, bedsNumberTextField, roomPriceTextField, addButton, deleteButton);

        return hBox;
    }

    private void loadTableItems() {
        rooms = FXCollections.observableArrayList(new RoomJPAdao().getAll());
        roomTableView.setItems(rooms);

    }

// pronadji projekat sa table view kad se cist graficki prikaz pa onda 
    private void onAddButtonClick(ActionEvent event) {
        String roomCode = roomNumberTextField.getText();
        RoomJPAdao roomJPAdao = new RoomJPAdao();
        Query query = roomJPAdao.getEntityManager().createNamedQuery("Room.findByCode");
        query.setParameter("code", roomCode);
        try {
            Room room = (Room) query.getSingleResult();
            if (roomCode.matches(room.getCode())) {
                MessageDialog messageDialog = new MessageDialog("Insert data alert", "Soba pod kodom " + roomCode + " već postoji!");
                messageDialog.display();
                clearInputs();
                return;
            }
        } catch (NoResultException e) {
            System.err.println("Nema takvog zapisa u tabel ROOM " + e.getMessage());
        }
        Room room = new Room();
        room.setCode(roomCode);
        room.setBeds(Integer.parseInt(bedsNumberTextField.getText()));
        room.setPrice(BigDecimal.valueOf(Double.parseDouble(roomPriceTextField.getText())));
        roomJPAdao.save(room);
        clearInputs();
        loadTableItems();
    }

   
    private void clearInputs() {
        roomNumberTextField.clear();
        roomPriceTextField.clear();
        bedsNumberTextField.clear();
    }

}
