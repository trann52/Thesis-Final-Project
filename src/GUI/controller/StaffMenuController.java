package GUI.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class StaffMenuController implements Initializable {

    String username;

    @FXML
    private Button printTicketsBtn;

    @FXML
    private Button luggCheckInBtn;

    @FXML
    private Button sortBtn;

    @FXML
    private Button loadBtn;

    @FXML
    private Button unloadBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Label userLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    // need to also log user out, not just send to loin page
    @FXML
    void logout(MouseEvent outEvent) throws IOException {
        Node n = (Node) outEvent.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/fxml/StaffLogin.fxml"));
        Scene scn = new Scene(root);
        Stage stg = (Stage) n.getScene().getWindow();
        stg.setScene(scn);
        stg.show();
    }

    @FXML
    void goToLuggTicket(MouseEvent luggTicEvent) throws IOException {
        Node n = (Node) luggTicEvent.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/fxml/LuggTickets.fxml"));
        Parent root = (Parent) loader.load();
        LuggTicketsController luggTicketsController = loader.getController();
        luggTicketsController.getUsername(userLabel.getText());
        Scene scn = new Scene(root);
        Stage stg = (Stage) n.getScene().getWindow();
        stg.setScene(scn);
        stg.show();

    }

    @FXML
    void goToLuggInfo(MouseEvent luggInfoEvent) throws IOException {
        Node n = (Node) luggInfoEvent.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/fxml/LuggInfo.fxml"));
        Scene scn = new Scene(root);
        Stage stg = (Stage) n.getScene().getWindow();
        stg.setScene(scn);
        stg.show();
    }


    public void getUsername (String passOnUsername) {
        username = passOnUsername;
        userLabel.setText(username);
    }


}
