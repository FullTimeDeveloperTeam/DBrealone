package express.expressfxml.manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class HomeViewController {
    @FXML Button empTable,conclude,logout;

    public void empTableBtn(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        try{
            ((Node)event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(this.getClass().getResource("../manager/WorkManagerView.fxml").openStream());
            primaryStage.setTitle("WorkManager");
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch ( IOException var6){
            var6.printStackTrace();
        }
    }

    public void concludeBtn(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        try{
            ((Node)event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(this.getClass().getResource("../manager/SumarizeView.fxml").openStream());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch ( IOException var6){
            var6.printStackTrace();
        }
    }

    public void logoutBtn(ActionEvent event) throws IOException {
        JOptionPane optionPane = new JOptionPane ();
        optionPane.setMessageType ( JOptionPane.QUESTION_MESSAGE );
        optionPane.setOptionType ( JOptionPane.YES_NO_OPTION );
        optionPane.setMessage ( "คุณต้องการ logout ใช่หรือไม่" );
        JDialog dialog = optionPane.createDialog ( null, "logout" );
        dialog.setVisible ( true );
        Integer selectedButton = ( Integer ) optionPane.getValue ( );
        if ( selectedButton == JOptionPane.YES_OPTION ) {
            Stage primaryStage = new Stage();
            try{
            ((Node)event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(this.getClass().getResource("../manager/LoginManagerView.fxml").openStream());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            }catch ( IOException var6){
                var6.printStackTrace();
            }
        }
        else if ( selectedButton == JOptionPane.NO_OPTION ){
            System.out.println("NO");
        }

    }

//    public void ChangeScene(ActionEvent event)throws IOException{
//        Stage primaryStage = new Stage();
//        try{
//        ((Node)event.getSource()).getScene().getWindow().hide();
//            FXMLLoader loader = new FXMLLoader();
//            Pane root = (Pane)loader.load(this.getClass().getResource("../manager/LoginManagerView.fxml").openStream());
//            Scene scene = new Scene(root);
//            primaryStage.setScene(scene);
//            primaryStage.show();
//        }catch ( IOException var6){
//            var6.printStackTrace();
//        }
//    }


}
