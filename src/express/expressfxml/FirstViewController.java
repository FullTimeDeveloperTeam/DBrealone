package express.expressfxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstViewController {
    @FXML
    Button managerButton,employeeButton;

    public void managerBtn(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        try{
            ((Node)event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(this.getClass().getResource("../expressfxml/manager/LoginManagerView.fxml").openStream());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch ( IOException var6){
            var6.printStackTrace();
        }
    }

    public void employeeBtn(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        try{
            ((Node)event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(this.getClass().getResource("../expressfxml/ScheduleView.fxml").openStream());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch ( IOException var6){
            var6.printStackTrace();
        }
    }
}
