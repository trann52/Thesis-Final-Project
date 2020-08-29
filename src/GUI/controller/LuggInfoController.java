package GUI.controller;

import Client.Client;
import Stream.SendMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Nicky Tran
 * @version 15/07/2020: 2.1
 * This class is the controller class for the 'LuggInfo' fxml, so it contains
 * all appropriate methods for the GUI such as moving between different GUI scenes.
 */
public class LuggInfoController implements Initializable {

    Client clientCommand = new Client();
    String username;
    String boardPassNumber;
    public static Connection connection;
    /**
     * Creating the lists of strings that will be shown in a drop down box
     */
    ObservableList<String> typeLuggList = FXCollections.observableArrayList("","Hand Luggage","Suitcase",
            "Oversized","Pet", "Other");
    ObservableList<String> fragileList = FXCollections.observableArrayList("","Yes", "No");
    ObservableList<String> excessList = FXCollections.observableArrayList( "","Yes","No");

    @FXML
    private Button menuBtn;

    @FXML
    private TextField bpnLabel;

    @FXML
    private TextField barNumLabel;

    @FXML
    private TextField weightLabel;

    @FXML
    private TextField colourLabel;

    @FXML
    private TextField dimenLabel;

    @FXML
    private Label warningLabel;

    @FXML
    private Button submitBtn;

    @FXML
    private Button nextBtn1;

    @FXML
    private ChoiceBox<String> typeLuggBox;

    @FXML
    private ChoiceBox<String> fragileBox;

    @FXML
    private ChoiceBox<String> excessBox;


    @FXML
    private Label userLabel;

    public LuggInfoController() throws IOException {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeLuggBox.setValue("");
        typeLuggBox.setItems(typeLuggList);
        fragileBox.setValue("");
        fragileBox.setItems(fragileList);
        excessBox.setValue("");
        excessBox.setItems(excessList);
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     *  The following methods will send the user to different scene depending on which button is clciked
     */

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
    void addToLuggInfoDatabase(MouseEvent event) throws NullPointerException, IOException, ClassNotFoundException {
        if (bpnLabel.getText().isEmpty() && barNumLabel.getText().isEmpty() && weightLabel.getText().isEmpty() &&
                fragileBox.getValue().isBlank() && excessBox.getValue().isBlank() && typeLuggBox.getValue().isBlank()
                || bpnLabel.getText().isEmpty() || barNumLabel.getText().isEmpty() || weightLabel.getText().isEmpty()
                || fragileBox.getValue().isBlank() || excessBox.getValue().isBlank() || typeLuggBox.getValue().isBlank())
        {
            warningLabel.setText("Fields with a * cannot be empty.");
        }
        else {
            clientCommand.clientAddToAboutLuggage(bpnLabel.getText(), barNumLabel.getText(), typeLuggBox.getValue(), weightLabel.getText(), colourLabel.getText(), dimenLabel.getText(), fragileBox.getValue(), excessBox.getValue());
            clientCommand.clientInsertToLuggageStatus2(bpnLabel.getText(), barNumLabel.getText());
            warningLabel.setText("Luggage Information successfully submitted.");
        }
        // need to change the else to add to database here (DONE)
        // need to connect server/client
    }

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
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * The following methods are for passing on information in between different scenes
     */

    public void getBookingNumber(String passOnBarcode){
        boardPassNumber = passOnBarcode;
        bpnLabel.setText(boardPassNumber);
    }

    public void getUsername (String passOnUsername) {
        username = passOnUsername;
        userLabel.setText(username);
    }




}
