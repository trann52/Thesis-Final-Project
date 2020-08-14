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
import java.sql.Array;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;

/**
 * @author Nicky Tran
 * @version 16/07/2020: 1.4
 * This class is the controller class for the 'Sorting' fxml, so it contains
 * all appropriate methods for the GUI such as moving between different GUI scenes.
 */
public class SortingController implements Initializable {
    Client clientCommand = new Client();
    String username;

    @FXML
    private Button menuBtn;

    @FXML
    private TextField barcodeLabel;

    @FXML
    private TextField sortByLabel;

    @FXML
    private TextField locationSortLabel;

    @FXML
    private Button sortSubmitBtn;

    @FXML
    private Label userLabel;

    @FXML
    private Label promptLabel;

    public SortingController() throws IOException {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        timeSortLabel.setText(dateTime);
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
    void sortAgain(MouseEvent sortMoreEvent) throws IOException, ClassNotFoundException {

        if (barcodeLabel.getText().isEmpty()) {
            promptLabel.setText("The barcode number cannot be empty.");
        } else {
            clientCommand.clientSortToLs(userLabel.getText(), barcodeLabel.getText(), locationSortLabel.getText());
            clientCommand.clientSortIntoVs(barcodeLabel.getText(), locationSortLabel.getText());
            Node n = (Node) sortMoreEvent.getSource();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/fxml/Sorting.fxml"));
            Parent root = (Parent) loader.load();
            SortingController sortingController = loader.getController();
            sortingController.getUsername(userLabel.getText());
            Scene scn = new Scene(root);
            Stage stg = (Stage) n.getScene().getWindow();
            stg.setScene(scn);
            stg.show();
            // need to also add data to database TODO
        }

    }


    public void getUsername(String passOnUsername) {
        username = passOnUsername;
        userLabel.setText(username);
        sortByLabel.setText(username);
    }


}
