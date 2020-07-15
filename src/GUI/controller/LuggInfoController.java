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

    @FXML
    private Button menuBtn;


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








}
