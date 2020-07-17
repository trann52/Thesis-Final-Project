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


public class LuggInfoController implements Initializable {

    String username;

    String boardPassNumber;

    @FXML
    private Button menuBtn;

    @FXML
    private TextField bpnLabel;

    @FXML
    private TextField barNumLabel;

    @FXML
    private TextField typeLabel;

    @FXML
    private TextField weightLabel;

    @FXML
    private TextField colourLabel;

    @FXML
    private TextField dimenLabel;

    @FXML
    private TextField fragileLabel;

    @FXML
    private TextField excessLabel;

    @FXML
    private Button submitBtn;

    @FXML
    private Button nextBtn1;

    @FXML
    private Label userLabel;


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

    // need to make method addLugageInfo() which adds all the data here into a database for the submit button

    @FXML
    void moreLuggInfo(MouseEvent event) throws IOException {
        Node n = (Node) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/fxml/LuggInfo.fxml"));
        Parent root = (Parent) loader.load();
        LuggInfoController luggInfoController = loader.getController();
        luggInfoController.getBookingNumber(bpnLabel.getText());
        luggInfoController.getUsername(userLabel.getText());
        Scene scn = new Scene(root);
        Stage stg = (Stage) n.getScene().getWindow();
        stg.setScene(scn);
        stg.show();
        // need to also update database when clicked
    }

    public void getBookingNumber(String passOnBarcode){
        boardPassNumber = passOnBarcode;
        bpnLabel.setText(boardPassNumber);
    }

    public void getUsername (String passOnUsername) {
        username = passOnUsername;
        userLabel.setText(username);
    }




}
