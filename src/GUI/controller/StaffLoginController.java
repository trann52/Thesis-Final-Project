package GUI.controller;

import Client.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * @author Nicky Tran
 * @version 13/07/2020: 1.2
 * This class is the controller class for the 'StaffLogin' fxml, so it contains
 * all appropriate methods for the GUI such as moving between different GUI scenes.
 */
public class StaffLoginController implements Initializable {

    Client clientCommand = new Client();

    @FXML
    private Button homeBtn;

    @FXML
    private TextField loginLabel;

    @FXML
    private Button confirm2btn;

    @FXML
    private Label promptLabel;

    @FXML
    private PasswordField passwordLabel;

    public StaffLoginController() throws IOException {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    void goHome(MouseEvent homeEvent) throws IOException {
        Node n = (Node) homeEvent.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/fxml/StartMenu.fxml"));
        Scene scn = new Scene(root);
        Stage stg = (Stage) n.getScene().getWindow();
        stg.setScene(scn);
        stg.show();
    }

    @FXML
    void checkAndConfirm(MouseEvent confirmEvent) throws IOException, ClassNotFoundException {

        String loginName = loginLabel.getText();
        String password = passwordLabel.getText();

        if (loginName.isEmpty() && password.isEmpty() || loginName.isEmpty() ||
                password.isEmpty()
                || !clientCommand.clientStaffLogin(loginName, password)
        ) {
            promptLabel.setText("Unable to login. Please try again.");
        } else{

            Node n = (Node) confirmEvent.getSource();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/fxml/StaffMenu.fxml"));
            Parent root = (Parent) loader.load();
            StaffMenuController staffMenuController = loader.getController();
            staffMenuController.getUsername(loginLabel.getText());
            Scene scn = new Scene(root);
            Stage stg = (Stage) n.getScene().getWindow();
            stg.setScene(scn);
            stg.show();

        }
    }






}
