package app.gui.user;

import app.business.dao.room.RoomJPAdao;
import app.model.Room;
import java.math.BigDecimal;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 *
 * @author Harun
 */
public class UserMainPanel extends VBox {

    private final Label titleLable = new Label("Room managemant");
    private final TableView<Room> roomTableView = new TableView<>();
    private ObservableList<Room> observableListRooms = null;
    private final TextField roomNumberTextField = new TextField();
    private final TextField bedsNumberTextField = new TextField();
    private final TextField roomPriceTextField = new TextField();
  
    private final Button addButton = new Button("Add room");
    private final Button deleteButton = new Button("Delete room");

    public UserMainPanel() {
        loadTableColumns();

    }

    private void loadTableItems() {
        RoomJPAdao roomJPAdao = new RoomJPAdao();
        List<Room> rooms = roomJPAdao.getAll();
        observableListRooms = FXCollections.observableArrayList();
        observableListRooms.addAll(rooms);
        roomTableView.setItems(observableListRooms);
    }

    private void loadTableColumns() {
        TableColumn<Room, String> roomNumberColumn = new TableColumn<>("Room number");
        roomNumberColumn.setCellValueFactory(new PropertyValueFactory<>("code"));

        TableColumn<Room, Integer> bedsNumberColumn = new TableColumn<>("Beds capacity");
        bedsNumberColumn.setCellValueFactory(new PropertyValueFactory<>("beds"));
        //nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        //nameColumn.setOnEditCommit(this::updateNameColumnCell);

        TableColumn<Room, BigDecimal> roomPriceColumn = new TableColumn<>("Room price");
        roomPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        roomTableView.getColumns().addAll(roomNumberColumn, bedsNumberColumn, roomPriceColumn);
        loadTableItems();
        titleLable.setFont(new Font(18));
        getChildren().addAll(titleLable, roomTableView, getAddRoomFormBox());

        roomTableView.setEditable(true);
    }

    public HBox getAddRoomFormBox() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10));
        hBox.setSpacing(5);
        roomNumberTextField.setPromptText("Room code...");
        bedsNumberTextField.setPromptText("Number of beds...");
        roomPriceTextField.setPromptText("Price per day...");
        hBox.getChildren().addAll(roomNumberTextField, bedsNumberTextField, roomPriceTextField, addButton, deleteButton);
        return hBox;
    }

    public HBox getSwitchPanelFormBox() {
        return null;
    }
}
