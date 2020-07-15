package GUI.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LuggInfoController implements Initializable {

    String bookingNumber;

    @FXML
    private Button menuBtn;

    @FXML
    private TextField pbnLabel;

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
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/fxml/StaffMenu.fxml"));
        Scene scn = new Scene(root);
        Stage stg = (Stage) n.getScene().getWindow();
        stg.setScene(scn);
        stg.show();
    }

    // need to make method addLugageInfo() which adds all the data here into a database

    @FXML
    void moreLuggInfo(MouseEvent event) throws IOException {
        Node n = (Node) event.getSource();
//        Parent root = FXMLLoader.load(getClass().getResource("/GUI/fxml/LuggInfo.fxml"));
//        Scene scn = new Scene(root);
//        Stage stg = (Stage) n.getScene().getWindow();
//        stg.setScene(scn);
//        stg.show();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/fxml/LuggInfo.fxml"));
        Parent root = (Parent) loader.load();
        LuggInfoController luggInfoController = loader.getController();
        luggInfoController.getBookingNumber(pbnLabel.getText());
        Scene scn = new Scene(root);
        Stage stg = (Stage) n.getScene().getWindow();
        stg.setScene(scn);
        stg.show();
    }

    public void getBookingNumber(String passOnBarcode){
        bookingNumber = passOnBarcode;
        pbnLabel.setText(bookingNumber);
    }






}
