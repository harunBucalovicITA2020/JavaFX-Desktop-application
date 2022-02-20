package app;

import app.business.Controller;
import app.gui.view.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX HotelApplication
 */
public class HotelApplication extends Application {

    @Override
    public void start(Stage stage) {
        LoginView loginView = new LoginView();
        Controller.getInstance().setLoginView(loginView);
        Controller.getInstance().setStage(stage);
        Scene scene = new Scene(loginView, 600, 180);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
