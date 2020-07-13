package GUI.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The start menu was based off a tutorial which can be found at: https://www.youtube.com/watch?v=YuiXnPefmhU
 */

public class StartMenuController implements Initializable {

    @FXML
    private Button passSelfCheckBtn;

    @FXML
    private Button staffLoginBtn;

    @FXML
    private Button luggStatusBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    void goToPassSelfCheckIn(MouseEvent passCheckEvent) throws IOException {
        Node node = (Node) passCheckEvent.getSource();
        Parent parent = FXMLLoader.load(getClass().getResource("/GUI/fxml/PassengerSelfCheckIn.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void goToStaffLogin(MouseEvent staffLoginEvent) throws IOException{
        Node node2 = (Node) staffLoginEvent.getSource();
        Parent parent = FXMLLoader.load(getClass().getResource("/GUI/fxml/StaffLogin.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) node2.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void goToLuggSearch(MouseEvent luggSearchEvent) throws IOException{
        Node node2 = (Node) luggSearchEvent.getSource();
        Parent parent = FXMLLoader.load(getClass().getResource("/GUI/fxml/LuggStatSearch.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) node2.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }





}
