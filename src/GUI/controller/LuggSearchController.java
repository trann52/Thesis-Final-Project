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
import java.util.ServiceLoader;

/**
 * @author Nicky Tran
 * @version 16/07/2020: 1.4
 * This class is the controller class for the 'LuggSearch' fxml, so it contains
 * all appropriate methods for the GUI such as moving between different GUI scenes.
 */
public class LuggSearchController implements Initializable {

    Client clientCommand;

    @FXML
    private Button homeBtn;


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
    void searchAndCheck(MouseEvent event) throws IOException, ClassNotFoundException {
        String barcodeNumber = barcodeLabel.getText();

        if (barcodeNumber.isEmpty() || clientCommand.clientGetViewStatus(barcodeNumber)) {
            promptLabel.setText("Unable to find luggage. Please try again.");
        }
        else {
            promptLabel.setText("Match found.");
        }
    }

    @FXML
    void goToCurrentStatus(MouseEvent statusEvent) throws IOException {
        Node n = (Node) statusEvent.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/fxml/CurrentStatus.fxml"));
        Parent root = (Parent) loader.load();
        CurrentStatusController currentStatusController = loader.getController();
        currentStatusController.getBarcode(barcodeLabel.getText());
        Scene scn = new Scene(root);
        Stage stg = (Stage) n.getScene().getWindow();
        stg.setScene(scn);
        stg.show();

    }





}
