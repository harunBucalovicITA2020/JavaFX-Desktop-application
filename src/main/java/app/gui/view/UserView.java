package app.gui.view;

import app.gui.user.UserMainPanel;
import app.gui.user.UserReservationPanel;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author Harun
 */
public class UserView extends BorderPane {

    private UserMainPanel mainPanel;
    private UserReservationPanel reservationPanel;
    private final Button logoutButton = new Button("Odjava");
    private final ToggleButton reservationButton = new ToggleButton("Reservations");
    private final ToggleButton roomButton = new ToggleButton("Rooms");

    public UserView() {
        mainPanel = new UserMainPanel();
        setCenter(mainPanel);

        ToggleGroup buttonToggleGroup = new ToggleGroup();

        reservationButton.setToggleGroup(buttonToggleGroup);
        reservationButton.setOnAction(this::onReservationButtonClick);
        roomButton.setToggleGroup(buttonToggleGroup);
        roomButton.setOnAction(this::onRoomButtonClick);
        roomButton.setSelected(true);

        HBox buttHBox = new HBox();
        buttHBox.setAlignment(Pos.CENTER_RIGHT);
        buttHBox.setPadding(new Insets(8));
        buttHBox.setSpacing(5);
        buttHBox.getChildren().addAll(roomButton, reservationButton, logoutButton);

        setTop(buttHBox);
        setBottom(reservationPanel);

    }

    private void onReservationButtonClick(ActionEvent event) {
        reservationPanel = new UserReservationPanel();
        setCenter(reservationPanel);
    }

    private void onRoomButtonClick(ActionEvent event) {
        mainPanel = new UserMainPanel();
        setCenter(mainPanel);
    }

    public UserMainPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(UserMainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public UserReservationPanel getReservationPanel() {
        return reservationPanel;
    }

    public void setReservationPanel(UserReservationPanel reservationPanel) {
        this.reservationPanel = reservationPanel;
    }

}
