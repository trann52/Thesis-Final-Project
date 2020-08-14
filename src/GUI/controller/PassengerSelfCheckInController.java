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
import java.util.ResourceBundle;

/**
 * @author Nicky Tran
 * @version 12/07/2020: 1.2
 * This class is the controller class for the 'PassengerSelfCheckIn' fxml, so it contains
 * all appropriate methods for the GUI such as moving between different GUI scenes.
 */
public class PassengerSelfCheckInController implements Initializable {

    Client clientCommand = new Client();

    @FXML
    private Button homeBtn;

    @FXML
    private TextField typeBookingHereLabel;

    @FXML
    private Button confirmBtn;

    @FXML
    private Label textLabel;

    public PassengerSelfCheckInController() throws IOException {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    /**
     * This method is attached to the home button. It will send the user to the StartMenu
     *
     * @param homeEvent The action of sending the user to the homepage
     * @throws IOException
     */
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
    void confirmAndNext(MouseEvent confirmEvent) throws IOException, ClassNotFoundException {

        String bookingNumber = typeBookingHereLabel.getText();

        if (bookingNumber.isEmpty()
                || clientCommand.clientBookingNumberCheck(bookingNumber)
        ) {
            textLabel.setText("Unable to find booking reference. \n Please try again");
        } else {
            Node n = (Node) confirmEvent.getSource();
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/fxml/PassSelfSuccess.fxml"));
            Scene scn = new Scene(root);
            Stage stg = (Stage) n.getScene().getWindow();
            stg.setScene(scn);
            stg.show();
        }

    }


}
