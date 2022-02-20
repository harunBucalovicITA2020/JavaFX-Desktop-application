package app.constants;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Harun
 */
public class MessageDialog {
    
    private final String title;
    private final String message;

    public MessageDialog(String title, String message) {
        this.title = title;
        this.message = message;
    }
    public void display(){
        Stage messageDialogStage = new Stage();
        messageDialogStage.setTitle(title);
        messageDialogStage.initModality(Modality.APPLICATION_MODAL);
        messageDialogStage.setWidth(300);
        messageDialogStage.setHeight(250);

        Label messageLabel = new Label(message);
        Button button = new Button("OK");
        button.setOnAction(actionEvent -> messageDialogStage.close());
        VBox vBox = new VBox(messageLabel,button);
        vBox.setSpacing(10);
        vBox.setPadding(Insets.EMPTY); // ovo radi -> ???
        vBox.setAlignment(Pos.CENTER);
        //vBox.getChildren().addAll(messageLabel,button);
        Scene scene = new Scene(vBox);
        messageDialogStage.setScene(scene);
        messageDialogStage.showAndWait();
    
    }
    
}
