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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;

/**
 * @author Nicky Tran
 * @version 27/07/2020: 1.3
 * This class is the controller class for the 'MissingSearch1' fxml, so it contains
 * all appropriate methods for the GUI such as moving between different GUI scenes.
 */
public class MissingSearch1Controller implements Initializable {

    Client clientCommand = new Client();
    String username;

    @FXML
    private Button menuBtn;

    @FXML
    private Label userLabel;

    @FXML
    private Button searchBtn;

    @FXML
    private Label promptLabel;

    @FXML
    private TextField barcodeLabel;

    @FXML
    private Button nextBtn;

    public MissingSearch1Controller() throws IOException {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        timeSortLabel.setText(dateTime);
    }


    /**
     * This method is attached to the home button. It will send the user to the StaffMenu
     * @param menuEvent The action of sending the user to the staff menu
     * @throws IOException
     */
    @FXML
    void goMenu(MouseEvent menuEvent) throws IOException {
        Node n = (Node) menuEvent.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/fxml/StaffMenu.fxml"));
        Parent root = (Parent) loader.load();
        StaffMenuController staffMenuController = loader.getController();
        staffMenuController.getUsername(userLabel.getText());
        Scene scn = new Scene(root);
        Stage stg = (Stage) n.getScene().getWindow();
        stg.setScene(scn);
        stg.show();
    }



    @FXML
    void searchAndCheck(MouseEvent event) {
        String barcodeNumber = barcodeLabel.getText();

        if (barcodeNumber.isEmpty()) {
            promptLabel.setText("Unable to find luggage. Please try again.");
        }
        else {
            promptLabel.setText("Match found.");
        }
    }


    @FXML
    void goToView(MouseEvent event) throws IOException, ClassNotFoundException {
        String barcodeNumber = barcodeLabel.getText();

        if (barcodeNumber.isEmpty()
                || clientCommand.clientGetLuggageStatus(barcodeNumber)
        ) {
            promptLabel.setText("Please enter luggage barcode.");
        }
        else {
            Node n = (Node) event.getSource();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/fxml/MissingView.fxml"));
            Parent root = (Parent) loader.load();
            MissingViewController missingViewController = loader.getController();
            missingViewController.getUsername(userLabel.getText());
            missingViewController.getBarcode(barcodeNumber);
            Scene scn = new Scene(root);
            Stage stg = (Stage) n.getScene().getWindow();
            stg.setScene(scn);
            stg.show();
        }


    }




    public void getUsername (String passOnUsername) {
        username = passOnUsername;
        userLabel.setText(username);
    }





}
