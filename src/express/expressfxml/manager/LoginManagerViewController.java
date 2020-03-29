package express.expressfxml.manager;

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

public class LoginManagerViewController extends Component {
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;

    private int checkLogin = 0;


    @FXML
    Button back, confirm, register;
    @FXML
    TextField username, password;

    public void start() {
        ConnectDb.connectDB();
    }

    public void backBtn(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        try {

            ((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(this.getClass().getResource("../../expressfxml/FirstView.fxml").openStream());
            Scene scene = new Scene(root);
            primaryStage.setTitle("Express");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException var6) {
            var6.printStackTrace();
        }
    }

    public void confirmBtn(ActionEvent event) throws IOException {
        con = ConnectDb.connectDB();
        String sql = "select * from user";

        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "กรอกข้อมูลให้ครบถ้วน", "แจ้งเตือน", JOptionPane.INFORMATION_MESSAGE);
            } else {
                while (rs.next()) {
                    if (rs.getString("User_name").equalsIgnoreCase(username.getText())
                            && rs.getString("password").equalsIgnoreCase(password.getText())) {
                        checkLogin++;
                    }
                }
                Stage primaryStage = new Stage();
                try {
                    if (checkLogin == 1) {
                        ((Node) event.getSource()).getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        Pane root = (Pane) loader.load(this.getClass().getResource("../manager/HomeView.fxml").openStream());
                        Scene scene = new Scene(root);
                        primaryStage.setScene(scene);
                        primaryStage.show();
                    } else {
                        JOptionPane.showMessageDialog(this, "ไม่พบผู้ใช้งาน", "แจ้งเตือน", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (SQLException var6) {
            var6.printStackTrace();
        }
    }

    public void registerBtn(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(this.getClass().getResource("../manager/RegisterView.fxml").openStream());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException var6) {
            var6.printStackTrace();
        }
    }
}
