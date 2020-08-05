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

/**
 * @author Nicky Tran
 * @version 16/07/2020: 1.3
 * This class is the controller class for the 'Loading' fxml, so it contains
 * all appropriate methods for the GUI such as moving between different GUI scenes.
 */
public class LoadingController implements Initializable {

    String username;

    @FXML
    private Button menuBtn;

    @FXML
    private TextField barcodeLabel;

    @FXML
    private TextField loadByLabel;

    @FXML
    private TextField locationLoadLabel;

    @FXML
    private Button loadSubmitBtn;

    @FXML
    private Label userLabel;

    @FXML
    private Label promptLabel;


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



    @FXML
    void loadAgain(MouseEvent loadMoreEvent) throws IOException {

        if (barcodeLabel.getText().isEmpty()) {
            promptLabel.setText("The barcode number cannot be empty.");
        } else {
            Node n = (Node) loadMoreEvent.getSource();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/fxml/Loading.fxml"));
            Parent root = (Parent) loader.load();
            LoadingController sortingController = loader.getController();
            sortingController.getUsername(userLabel.getText());
            Scene scn = new Scene(root);
            Stage stg = (Stage) n.getScene().getWindow();
            stg.setScene(scn);
            stg.show();
        }
    }



    public void getUsername (String passOnUsername) {
        username = passOnUsername;
        userLabel.setText(username);
        loadByLabel.setText(username);
    }




}
