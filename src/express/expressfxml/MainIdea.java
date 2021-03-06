package express.expressfxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainIdea extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FirstView.fxml"));
        primaryStage.setScene(new Scene(root, 480, 480));
        primaryStage.setTitle("Express");
        primaryStage.show();
    }
}
