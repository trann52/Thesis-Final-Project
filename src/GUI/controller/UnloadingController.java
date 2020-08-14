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
import java.util.ResourceBundle;

/**
 * @author Nicky Tran
 * @version 16/07/2020: 1.3
 * This class is the controller class for the 'Unloading' fxml, so it contains
 * all appropriate methods for the GUI such as moving between different GUI scenes.
 */
public class UnloadingController implements Initializable {

    Client clientCommand = new Client();
    String username;

    @FXML
    private Button menuBtn;

    @FXML
    private TextField barcodeLabel;

    @FXML
    private TextField unloadByLabel;

    @FXML
    private TextField locationUnloadLabel;

    @FXML
    private Button loadSubmitBtn;

    @FXML
    private Label userLabel;

    @FXML
    private Label promptLabel;

    public UnloadingController() throws IOException {
    }


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
    void unloadAgain(MouseEvent unloadMoreEvent) throws IOException, ClassNotFoundException {

        if (barcodeLabel.getText().isEmpty()) {
            promptLabel.setText("The barcode number cannot be empty.");
        } else {
            clientCommand.clientUnloadToLs(userLabel.getText(), barcodeLabel.getText(), locationUnloadLabel.getText());
            clientCommand.clientUnloadIntoVs(barcodeLabel.getText(), locationUnloadLabel.getText());
            Node n = (Node) unloadMoreEvent.getSource();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/fxml/Unloading.fxml"));
            Parent root = (Parent) loader.load();
            UnloadingController unloadingController = loader.getController();
            unloadingController.getUsername(userLabel.getText());
            Scene scn = new Scene(root);
            Stage stg = (Stage) n.getScene().getWindow();
            stg.setScene(scn);
            stg.show();
        }
    }


    public void getUsername(String passOnUsername) {
        username = passOnUsername;
        userLabel.setText(username);
        unloadByLabel.setText(username);
    }


}
