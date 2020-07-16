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


public class LuggTicketsController implements Initializable {

    String username;

    @FXML
    private Button menuBtn;

    @FXML
    private TextField typeBookingHereLabel;

    @FXML
    private Button confirmBtn;

    @FXML
    private Label textLabel;

    @FXML
    private Button nextBtn;

    @FXML
    private Label userLabel;

//    private String bookingNumber = typeBookingHereLabel.getText();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    /**
     * This method is attached to the home button. It will send the user to the StaffMenu
     *
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
    void confirmAndPrint(MouseEvent confirmEvent) throws IOException {
        String bookingNumber = typeBookingHereLabel.getText();

        if (bookingNumber.isEmpty()) {
            textLabel.setText("Unable to find booking reference. \n Please try again");
        }
        // else statement should be a print
        else {
            textLabel.setText("Booking found.");
        }
    }

    @FXML
    void goToNext(MouseEvent nextEvent) throws IOException {
        String bookingNumber = typeBookingHereLabel.getText();

        if (bookingNumber.isEmpty()){
            textLabel.setText("Please enter a booking reference.");
        }
        else {
            Node n = (Node) nextEvent.getSource();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/fxml/LuggInfo.fxml"));
            Parent root = (Parent) loader.load();
            LuggInfoController luggInfoController = loader.getController();
            luggInfoController.getBookingNumber(typeBookingHereLabel.getText());
            luggInfoController.getUsername(userLabel.getText());
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
