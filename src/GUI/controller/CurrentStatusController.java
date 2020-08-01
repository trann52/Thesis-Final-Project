package GUI.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * @author Nicky Tran
 * @version 17/07/2020: 1.1
 * This class is the controller class for the 'CurrentStatus' fxml, so it contains
 * all appropriate methods for the GUI such as moving between different GUI scenes.
 */
public class CurrentStatusController implements Initializable {

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button homeBtn;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    void goHome(MouseEvent homeEvent) throws IOException {
        Node n = (Node) homeEvent.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/fxml/StartMenu.fxml"));
        Scene scn = new Scene(root);
        Stage stg = (Stage) n.getScene().getWindow();
        stg.setScene(scn);
        stg.show();
    }

    public void testBar(){
        progressBar.setProgress(0.5);
    }
}
