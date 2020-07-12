package GUI.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The start menu was based off a tutorial which can be found at: https://www.youtube.com/watch?v=YuiXnPefmhU
 */

public class PassengerSelfCheckInController implements Initializable {

    @FXML
    private Button homeBtn;

    @FXML
    private TextField typeBookingHereLabel;

    @FXML
    private Button confirmBtn;

    @FXML
    private Label textLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    void goHome(MouseEvent homeEvent) throws IOException {
        Node node = (Node) homeEvent.getSource();
        Parent parent = FXMLLoader.load(getClass().getResource("/GUI/fxml/StartMenu.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void confirmAndNext(MouseEvent confirmEvent) throws IOException {
        Node node2 = (Node) confirmEvent.getSource();
        Parent parent = FXMLLoader.load(getClass().getResource("/GUI/fxml/PassSelfSuccess.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) node2.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

//    @FXML
//    void confirmAndNext(MouseEvent confirmEvent) throws IOException {
//        Node node2 = (Node) confirmEvent.getSource();
//        Parent parent = FXMLLoader.load(getClass().getResource("/GUI/fxml/PassSelfSuccess.fxml"));
//        Scene scene = new Scene(parent);
//        Stage stage = (Stage) node2.getScene().getWindow();
//        stage.setScene(scene);
//        stage.show();
//    }



}
