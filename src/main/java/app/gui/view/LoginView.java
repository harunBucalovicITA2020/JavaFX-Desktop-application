/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.gui.view;

import app.business.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Harun
 */
public class LoginView extends GridPane {

    private Label usernameLabel = new Label("Username:");
    private Label passwordLable = new Label("Password:");
    private Label messageLabel = new Label("");
    private TextField usernameTextField = new TextField();
    private PasswordField passwordField = new PasswordField();
    private Button loginButton = new Button("Login");
    private Button cancleButton = new Button("Cancle");

    public LoginView() {
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(25));

        add(usernameLabel, 0, 0);
        add(usernameTextField, 1, 0);

        add(passwordLable, 0, 1);
        add(passwordField, 1, 1);

        FlowPane flowPane = new FlowPane();
        flowPane.setAlignment(Pos.CENTER_RIGHT);
        flowPane.getChildren().addAll(loginButton, cancleButton);
        add(flowPane, 1, 2);

        add(messageLabel, 1, 3);

        loginButton.setOnAction(Controller.getInstance().getEventBus().getLoginEvent());
        cancleButton.setOnAction(Controller.getInstance().getEventBus().getCancleEvent());

    }

    public TextField getUsernameTextField() {
        return usernameTextField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Label getMessageLabel() {
        return messageLabel;
    }
    
    /*
     public String getUsername() {
        return usernameTextField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }
*/

    public Label getUsernameLabel() {
        return usernameLabel;
    }

    public void setUsernameLabel(Label usernameLabel) {
        this.usernameLabel = usernameLabel;
    }

    public Label getPasswordLable() {
        return passwordLable;
    }

    public void setPasswordLable(Label passwordLable) {
        this.passwordLable = passwordLable;
    }
}


