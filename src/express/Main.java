package express;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main extends Application {
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;

    public Main (){
        con =ConnectDb.connectDB();
    }
    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource(""));
        primaryStage.setTitle("Express");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        ConnectDb connectDb;

        launch(args);
    }
}
