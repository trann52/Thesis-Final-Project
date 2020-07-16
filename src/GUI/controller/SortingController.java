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


public class SortingController implements Initializable {

    String username;

    @FXML
    private Button menuBtn;

    @FXML
    private TextField barcodeLabel;

    @FXML
    private TextField sortByLabel;

    @FXML
    private TextField timeSortLabel;

    @FXML
    private Button sortSubmitBtn;

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

    // need to make method addLugageInfo() which adds all the data here into a database

    @FXML
    void sortAgain(MouseEvent sortMoreEvent) throws IOException {
        Node n = (Node) sortMoreEvent.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/fxml/Sorting.fxml"));
        Parent root = (Parent) loader.load();
        SortingController sortingController = loader.getController();
        sortingController.getUsername(userLabel.getText());
        Scene scn = new Scene(root);
        Stage stg = (Stage) n.getScene().getWindow();
        stg.setScene(scn);
        stg.show();
    }



    public void getUsername (String passOnUsername) {
        username = passOnUsername;
        userLabel.setText(username);
        sortByLabel.setText(username);
    }




}
