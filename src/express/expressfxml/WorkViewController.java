package express.expressfxml;

import express.ConnectDb;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkViewController {
    @FXML Button okBtn, backBtn,fourWheel,sixWheel,tenWheel,specialWheel;
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    public void confirmBtn(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
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
    }

    public void backBtn(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
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
    }
    public void showTableCar(){
        try{
            con = ConnectDb.connectDB();
            String sql = "SELECT  ";
            pst = con.prepareStatement(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void insertFourCar(){
        try {
            con = ConnectDb.connectDB();
            String sql ="INSERT INTO ticket VALUES ('4Wheel' ,'4Wheel',30) ";
            pst = con.prepareStatement(sql);
            pst.execute();





            pst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void insertSixCar(){
        try {
            con = ConnectDb.connectDB();
            String sql = "INSERT INTO ";
            pst = con.prepareStatement(sql);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertTenCar(){

    }


    public void insertSpecialCar(){

    }


}
