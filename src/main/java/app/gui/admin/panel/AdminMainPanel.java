package app.gui.admin.panel;

import app.business.Controller;
import app.business.dao.privilege.PrivilegeJPAdao;
import app.business.dao.user.UserJPADao;
import app.model.Privilege;
import app.model.User;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 *
 * @author Harun
 */
public class AdminMainPanel extends VBox {

    private final Label titleLable = new Label("Korisnička administracija");
    private TableView<User> userTableView = new TableView<>();
    private ObservableList<User> userObservableList = null;
    private TextField usernameTextFieldInput = new TextField();
    private PasswordField passwordFieldInput = new PasswordField();
    private TextField nameTextFieldInput = new TextField();
    private TextField surenameTextFieldInput = new TextField();
    private ComboBox<Privilege> privilegeComboBox = new ComboBox<>();

    private Button addUserButton = new Button("Add");
    private Button removeUserButton = new Button("Delete");

    public AdminMainPanel() {
        setPadding(new Insets(5));
        TableColumn<User, String> usernameColumn = new TableColumn<>("Korisničko ime");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));

        TableColumn<User, String> nameColumn = new TableColumn<>("Ime");
        nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(this::updateNameColumnCell);

        TableColumn<User, String> surenameColumn = new TableColumn<User, String>("Prezime");
        surenameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));

        TableColumn<User, String> privilegeColumn = new TableColumn<>("Privilegija");
        privilegeColumn.setCellValueFactory(new PropertyValueFactory<>("idPrivilege"));

        userTableView.getColumns().addAll(usernameColumn, nameColumn, surenameColumn, privilegeColumn);
        loadTableItems();
        titleLable.setPadding(new Insets(7));
        //titleLable.setPrefHeight(10);
        titleLable.setFont(new Font(18));
        getChildren().addAll(titleLable, userTableView, getFormBox());
        userTableView.setEditable(true);

    }

    private HBox getFormBox() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10));
        hBox.setSpacing(3);
        usernameTextFieldInput.setMaxWidth(100);
        usernameTextFieldInput.setPromptText("Korisničko ime...");
        passwordFieldInput.setMaxWidth(100);
        passwordFieldInput.setPromptText("Lozinka...");
        nameTextFieldInput.setMaxWidth(100);
        nameTextFieldInput.setPromptText("Ime...");
        surenameTextFieldInput.setMaxWidth(100);
        surenameTextFieldInput.setPromptText("Prezime");
        //Privilegije stavljam u CHOICHE BOX -> ON PRIMA OBSERVABILNU LISTU -> OB LISTA PRIMA OBICNU LISTU(java.util.arrayList)
        //Listu privilegija iz baze proslijedjujem obzervabilnoj listi ---->izolovano u funkciju loadPrivilege()
        privilegeComboBox.setMaxWidth(USE_PREF_SIZE);
        privilegeComboBox.setPromptText("Odaberi privilegiju");
        loadPrivilege();
        addUserButton.setOnAction(this::addOrUpdateUser);
        removeUserButton.setOnAction(this::deleteUser);
        hBox.getChildren()
                .addAll(usernameTextFieldInput,
                        passwordFieldInput,
                        nameTextFieldInput,
                        surenameTextFieldInput,
                        privilegeComboBox,
                        addUserButton,
                        removeUserButton);
        return hBox;

    }

    private void loadTableItems() {
        userObservableList = FXCollections.observableArrayList(new UserJPADao().getAll());
        userObservableList.remove(Controller.getInstance().getLoggedUser());
        userTableView.setItems(userObservableList);

    }

    private void loadPrivilege() {
        PrivilegeJPAdao privilegeJPAdao = new PrivilegeJPAdao();
        List<Privilege> privileges = privilegeJPAdao.getAll();
        ObservableList<Privilege> privilegeObservableList = FXCollections.observableArrayList(privileges);
        privilegeComboBox.setItems(privilegeObservableList);
    }

    private void deleteUser(ActionEvent deleteActionEvent) {
        User user = userTableView.getSelectionModel().getSelectedItem();
        if (user != null) {
            UserJPADao userJPADao = new UserJPADao();
            userJPADao.delete(user);
        }
        loadTableItems();
    }

    private void clearInputs() {
        usernameTextFieldInput.clear();
        passwordFieldInput.clear();
        nameTextFieldInput.clear();
        surenameTextFieldInput.clear();
    }

    private void addOrUpdateUser(ActionEvent event) {
        //DODAJEM USERA KOJI NE POSTIJI
        String username = usernameTextFieldInput.getText();
        UserJPADao userJPADao = new UserJPADao();
        User user = userJPADao.loginUserByUsernameOnly(username);
        if (user == null) {
            user = new User();
        }
        user.setUsername(usernameTextFieldInput.getText());
        user.setPassword(passwordFieldInput.getText());
        user.setName(nameTextFieldInput.getText());
        user.setSurname(surenameTextFieldInput.getText());
        user.setIdPrivilege(privilegeComboBox.getSelectionModel().getSelectedItem());

        //UPDATE POSTOJEĆEG USERA
        if (user.getId() != null) {
            userJPADao.update(user);
        } //NA KRAJU SPAČAVAM USERA,BILO DA JE KREIRAN NOVI USER ILI UPDEJTOVAN POSTOJEĆI 
        else {
            userJPADao.save(user);
        }
        clearInputs();
        loadTableItems();
    }
    
    
    public void updateNameColumnCell(TableColumn.CellEditEvent<User, String> event) {
        event.getTableView().getItems().get(event.getTablePosition().getRow()).setName(event.getNewValue());
        UserJPADao userJPADao = new UserJPADao();
        User user = userJPADao.get(event.getRowValue().getId().intValue());
        System.out.println("Sta si dobio kao rezultat " + user.getName());
        user.setName(event.getNewValue());
        userJPADao.update(user);

    }

}
