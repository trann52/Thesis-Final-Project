import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;


/**
 * @author Nicky Tran
 * @version 11/07/2020: 1.1
 * This class is the main class which will load the StartMenu GUI, basically the running of the entire application.
 */
public class Main extends Application {


    @Override
    public void start(Stage initialStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/fxml/StartMenu.fxml"));


        Scene scene = new Scene(root);
        initialStage.setScene(scene);
        initialStage.setTitle("Luggage System");
        initialStage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }

}
