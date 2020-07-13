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

    /**
     * When the passSelfCheckBtn is clicked the user is sent to the PassengerSelfCheckin scene in the
     * same window
     * @param passCheckEvent The action of clicking the button
     * @throws IOException
     */
    @FXML
    void goToPassSelfCheckIn(MouseEvent passCheckEvent) throws IOException {
        Node n = (Node) passCheckEvent.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/fxml/PassengerSelfCheckIn.fxml"));
        Scene scn = new Scene(root);
        Stage stg = (Stage) n.getScene().getWindow();
        stg.setScene(scn);
        stg.show();
    }

    /**
     * When the staffLoginBtn is clicked the user is sent to the StaffLogin page
     * @param staffLoginEvent The action of clicking the button
     * @throws IOException
     */
    @FXML
    void goToStaffLogin(MouseEvent staffLoginEvent) throws IOException{
        Node n = (Node) staffLoginEvent.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/fxml/StaffLogin.fxml"));
        Scene scene = new Scene(root);
        Stage stg = (Stage) n.getScene().getWindow();
        stg.setScene(scene);
        stg.show();
    }

    /**
     * When the luggStatusBtn is clicked the user is sent to the LuggStatSearch page
     * @param luggSearchEvent
     * @throws IOException
     */
    @FXML
    void goToLuggSearch(MouseEvent luggSearchEvent) throws IOException{
        Node n = (Node) luggSearchEvent.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/fxml/LuggStatSearch.fxml"));
        Scene scn = new Scene(root);
        Stage stg = (Stage) n.getScene().getWindow();
        stg.setScene(scn);
        stg.show();
    }



}
