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
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;

/**
 * @author Nicky Tran
 * @version 29/07/2020: 1.1
 * This class is the controller class for the 'Destination' fxml, so it contains
 * all appropriate methods for the GUI such as moving between different GUI scenes.
 */


public class DestinationController implements Initializable {

    String username;
    String dateTime = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).format(ZonedDateTime.now());

    @FXML
    private Button menuBtn;

    @FXML
    private TextField barcodeLabel;

    @FXML
    private TextField locationLabel;

    @FXML
    private TextField timeSortLabel;

    @FXML
    private Button destSubmitBtn;

    @FXML
    private Label userLabel;

    @FXML
    private Label promptLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
    void destAgain(MouseEvent destMoreEvent) throws IOException {

        if (barcodeLabel.getText().isEmpty() && locationLabel.getText().isEmpty()) {
            promptLabel.setText("The barcode number and location cannot be empty.");
        }
        else if (barcodeLabel.getText().isEmpty()){
            promptLabel.setText("The location cannot be empty.");
        }
        else if (locationLabel.getText().isEmpty()){
            promptLabel.setText("The barcode number cannot be empty.");
        }
        else {
            Node n = (Node) destMoreEvent.getSource();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/fxml/Destination.fxml"));
            Parent root = (Parent) loader.load();
            DestinationController destinationController = loader.getController();
            destinationController.getUsername(userLabel.getText());
            Scene scn = new Scene(root);
            Stage stg = (Stage) n.getScene().getWindow();
            stg.setScene(scn);
            stg.show();
            // need to also add data to database TODO
        }
    }



    public void getUsername (String passOnUsername) {
        username = passOnUsername;
        userLabel.setText(username);
    }





}