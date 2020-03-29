package express.expressfxml;

import express.ConnectDb;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginViewController extends Component {
    @FXML
    Button backBtn,confirmBtn;
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private int checkLogin = 0;
    @FXML
    TextField idemp;



    public void backBtn(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        try{
            ((Node)event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(this.getClass().getResource("../expressfxml/FirstView.fxml").openStream());
            primaryStage.setTitle("Express");
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch ( IOException var6){
            var6.printStackTrace();
        }
    }

    public void confirmBtn(ActionEvent event) throws IOException {
        con = ConnectDb.connectDB();
        String sql = "select emp_id from employee";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (idemp.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "กรอกข้อมูลให้ครบถ้วน", "แจ้งเตือน", JOptionPane.INFORMATION_MESSAGE);
            } else {
                while (rs.next()) {
                    if (rs.getString("emp_id").equalsIgnoreCase(idemp.getText())) {
                        checkLogin++;
                    }
                }
            }
            Stage primaryStage = new Stage();
            try {
                if (checkLogin == 1) {
                    ((Node) event.getSource()).getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    String idToname = idemp.getText();
                    Pane root = (Pane) loader.load(this.getClass().getResource("../expressfxml/NameView.fxml").openStream());
                    NameViewController nameViewController =loader.getController();
                    nameViewController.showId(idToname);
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } else {
                    JOptionPane.showMessageDialog(this, "ไม่พบผู้ใช้งาน", "แจ้งเตือน", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (SQLException var6) {
            var6.printStackTrace();
        }
    }




   /* Stage primaryStage = new Stage();
        try{
            ((Node)event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(this.getClass().getResource("../expressfxml/NameView.fxml").openStream());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch ( IOException var6){
            var6.printStackTrace();
        }
    }*/


}
