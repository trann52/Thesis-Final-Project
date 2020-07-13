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

    /**
     * This method is attached to the home button. It will send the user to the StartMenu
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
    void confirmAndNext(MouseEvent confirmEvent) throws IOException {

        String bookingRef = typeBookingHereLabel.getText();

        if (bookingRef.isEmpty()){
            textLabel.setText("Unable to find booking reference. \n Please try again");
        }
        else {
            Node n = (Node) confirmEvent.getSource();
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/fxml/PassSelfSuccess.fxml"));
            Scene scn = new Scene(root);
            Stage stg = (Stage) n.getScene().getWindow();
            stg.setScene(scn);
            stg.show();
        }

    }




}
