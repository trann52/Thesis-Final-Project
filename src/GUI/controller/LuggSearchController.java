package GUI.controller;

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

public class LuggSearchController implements Initializable {


    @FXML
    private Button homeBtn;

    @FXML
    private TextField boardPassNumberLabel;

    @FXML
    private Button searchBtn;

    @FXML
    private Label promptLabel;

    @FXML
    private TextField barcodeLabel;

    @FXML
    private Button nextBtn;



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
    void searchAndCheck(MouseEvent event) {
        String boardPassNumber = boardPassNumberLabel.getText();
        String barcodeNumber = barcodeLabel.getText();

        if (boardPassNumber.isEmpty() && barcodeNumber.isEmpty() || boardPassNumber.isEmpty() ||
        barcodeNumber.isEmpty()) {
            promptLabel.setText("Unable to find luggage. Please try again");
        }
        else {
            promptLabel.setText("Match found.");
        }

    }







}
