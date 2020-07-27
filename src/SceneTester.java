import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneTester extends Application {


    @Override
    public void start(Stage initialStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/fxml/MissingView.fxml"));


        Scene scene = new Scene(root);
        initialStage.setScene(scene);
        initialStage.setTitle("Luggage System");
        initialStage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }

}
