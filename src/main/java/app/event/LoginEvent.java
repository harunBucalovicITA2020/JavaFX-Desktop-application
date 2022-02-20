/*
 * kreiranje VIEW-a koristenjem ENUM-a
 if (user == null) {
            loginView.getMessageLabel().setText(Constants.BAD_USERNAME_PASSWORD_COMBINATION);
        } else {
            Controller.getInstance().setLoggedUser(user);
            Privilege privilege = user.getIdPrivilege();
            BorderPane borderPaneView = ViewFactory.loadView(privilege);
            Scene scene = new Scene(borderPaneView, 650, 250);
            Controller.getInstance().getStage().setScene(scene);
        }

    }
 */
package app.event;

import app.business.Controller;
import app.business.dao.user.UserJPADao;
import app.constants.Constants;
import app.gui.view.LoginView;
import app.gui.view.AdminView;
import app.gui.view.UserView;
import app.model.Privilege;
import app.model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Harun
 */
public class LoginEvent implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {

        LoginView loginView = Controller.getInstance().getLoginView();
        String username = loginView.getUsernameTextField().getText();
        //String password = loginView.getPassword();
        String password = loginView.getPasswordField().getText();

        if (username.isEmpty() || password.isEmpty()) {
            loginView.getMessageLabel().setText(Constants.EMPTY_LOGIN_FIELDS_MESSAGE);
            return;
        }
        UserJPADao jpaUserDao = new UserJPADao();
        User user = jpaUserDao.loginUser(username, password);
        if (user == null) {
            loginView.getMessageLabel().setText(Constants.BAD_USERNAME_PASSWORD_COMBINATION);
        } else {
            Controller.getInstance().setLoggedUser(user);
            Privilege privilege = user.getIdPrivilege();
            BorderPane borderPaneView;
            if ("admin".equals(privilege.getName())) {
                borderPaneView = new AdminView();
                //  Controller.getInstance().setAdminView((AdminView) borderPaneView);
                Controller.getInstance().getStage().setTitle("ADMIN : " + user.getName());
            } else {

                borderPaneView = new UserView();
                //Controller.getInstance().setUserView((UserView) borderPaneView);
                Controller.getInstance().getStage().setTitle("USER : " + user.getName());
            }
            Scene scen = new Scene(borderPaneView, 650, 400);
            Controller.getInstance().getStage().setScene(scen);
        }

    }
}
