/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.gui.view;

import app.business.Controller;
import app.gui.admin.panel.AdminMainPanel;
import app.gui.admin.panel.AdminRoomPanel;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author Harun
 */
public class AdminView extends BorderPane {

    private ToggleButton userToggleButton = new ToggleButton("Korisnici");
    private ToggleButton roomToggleButton = new ToggleButton("Sobe");
    private Button logoutButton = new Button("Odjava");
    
    private AdminMainPanel adminPanel;
    private AdminRoomPanel roomPanel;
    
    public AdminView() {
        adminPanel = new AdminMainPanel();
        setCenter(adminPanel);
        
        ToggleGroup toggleGroup = new ToggleGroup();
        userToggleButton.setToggleGroup(toggleGroup);
        userToggleButton.setSelected(true);
        userToggleButton.setOnAction(this::onUserToggleButtonClick);
        roomToggleButton.setToggleGroup(toggleGroup);
        roomToggleButton.setOnAction(this::onRoomToggleButtonClick);
        
        HBox mainmenue = new HBox();
        mainmenue.setSpacing(5);
        mainmenue.setPadding(new Insets(10));
        mainmenue.getChildren().addAll(userToggleButton, roomToggleButton);
        
        logoutButton.setText("Odjava (" + Controller.getInstance().getLoggedUser() + ")");
        logoutButton.setOnAction(Controller.getInstance().getEventBus().getLogoutEvent() );
        
        HBox logoutBox = new HBox(); 
        logoutBox.getChildren().add(logoutButton);
        logoutBox.setAlignment(Pos.CENTER);
        logoutBox.setPadding(new Insets(10));
        
        GridPane gridPane = new GridPane();
        gridPane.add(mainmenue, 0, 0);
        gridPane.add(logoutBox, 1, 0);
        
        setTop(gridPane);
       
    }

    public void onRoomToggleButtonClick(ActionEvent event) {
        roomPanel = new AdminRoomPanel();
        setCenter(roomPanel);
        
    }
    
    public void onUserToggleButtonClick(ActionEvent event) {
        adminPanel = new AdminMainPanel();
        setCenter(adminPanel);
    }
    
    public AdminMainPanel getAdminPanel() {
        return adminPanel;
    }
    
    public void setAdminPanel(AdminMainPanel adminPanel) {
        this.adminPanel = adminPanel;
    }
    
    public AdminRoomPanel getRoomPanel() {
        return roomPanel;
    }
    
    public void setRoomPanel(AdminRoomPanel roomPanel) {
        this.roomPanel = roomPanel;
    }
    
}
