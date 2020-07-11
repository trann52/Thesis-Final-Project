package GUI.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The start menu was based off a tutorial which can be found at: https://www.youtube.com/watch?v=YuiXnPefmhU
 */

public class StartMenuController implements Initializable {

    @FXML
    private Button passSelfCheckBtn;

    @FXML
    private Button staffLoginBtn;

    @FXML
    private Button LuggStatusBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}
