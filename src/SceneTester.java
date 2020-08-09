import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * @author Nicky Tran
 * @version 12/07/2020: 1.1
 * This class is a tester class that will load in any GUI scene using the appropriate fxml
 * file location to ensure that methods for that fxml correctly work. This will help visualise
 * the fxml and will make any issues in the fxml shown.
 */
public class SceneTester extends Application {


    @Override
    public void start(Stage initialStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/fxml/LuggInfo.fxml"));


        Scene scene = new Scene(root);
        initialStage.setScene(scene);
        initialStage.setTitle("Luggage System");
        initialStage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }

}
