/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.business;

import app.event.EventBus;
import app.gui.view.LoginView;
import app.gui.view.AdminView;
import app.gui.view.UserView;
import app.model.User;
import javafx.stage.Stage;

/**
 *
 * @author Harun
 */
public class Controller {

    private static Controller INSTANCE = null;

    private LoginView loginView;
    private AdminView adminView;
    private UserView userView;
    private User loggedUser;
    private final EventBus eventBus = new EventBus();
    private Stage stage;

    public Controller() {
        super();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public LoginView getLoginView() {
        return loginView;
    }

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    public AdminView getAdminView() {
        return adminView;
    }

    public void setAdminView(AdminView adminView) {
        this.adminView = adminView;
    }

    public UserView getUserView() {
        return userView;
    }

    public void setUserView(UserView userView) {
        this.userView = userView;
    }

    public User getUser() {
        return loggedUser;
    }

    public void setUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public static Controller getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Controller();
        }
        return INSTANCE;
    }

    public EventBus getEventBus() {
        return eventBus;
    }
    
}
