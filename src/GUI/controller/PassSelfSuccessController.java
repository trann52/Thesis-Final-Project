package GUI.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class PassSelfSuccessController implements Initializable {


    @FXML
    private Button homeBtn;

    @FXML
    private Text infoLabel;

    @FXML
    private Text infoLabel1;

    @FXML
    private Button printBtn;


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

    // need to add in the print method (best inherit it from the printer class)

}
