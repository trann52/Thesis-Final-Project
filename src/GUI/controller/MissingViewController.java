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


public class MissingViewController implements Initializable {

    String username;
    String dateTime = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).format(ZonedDateTime.now());

    @FXML
    private Button menuBtn;

    @FXML
    private Label userLabel;

    @FXML
    private Button searchAgainBtn;



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
    void backToMissSearch(MouseEvent missingEvent) throws IOException {
        Node n = (Node) missingEvent.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/fxml/MissingSearch1.fxml"));
        Parent root = (Parent) loader.load();
        MissingSearch1Controller missingSearchController = loader.getController();
        missingSearchController.getUsername(userLabel.getText());
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
