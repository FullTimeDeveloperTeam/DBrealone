package express.expressfxml;

import express.ConnectDb;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkViewController {
    @FXML Button okBtn, backBtn,fourWheel,sixWheel,tenWheel,specialWheel;
    @FXML
    DatePicker dateAddTable;
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
        String value = dateAddTable.getValue().toString();
        try{
            con = ConnectDb.connectDB();
            String sql = "SELECT  ";
            pst = con.prepareStatement(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    String value1 = "";
    String value2 = "";
    String value3 = "";
    public void insertFourCar(ActionEvent event) throws SQLException {
        String value4 = "1";
        try{
            String sql = "INSERT INTO summarize  VALUES (?,?,?,? )";
            con = ConnectDb.connectDB();
            pst =con.prepareStatement(sql);
            pst.setString(1,value1);
            pst.setString(2,value2);
            pst.setString(3,value3);
            pst.setString(3,value4);
            pst.execute();
            System.out.println("INSERT CORRECT");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("INSERT FAIL");
        }
    }

    public void insertSixCar(){
        String value4 = "2";
        try{
            String sql = "INSERT INTO summarize  VALUES (?,?,?,? )";
            con = ConnectDb.connectDB();
            pst =con.prepareStatement(sql);
            pst.setString(1,value1);
            pst.setString(2,value2);
            pst.setString(3,value3);
            pst.setString(3,value4);
            pst.execute();
            System.out.println("INSERT CORRECT");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("INSERT FAIL");
        }
    }

    public void insertTenCar(){
        String value4 = "3";
        try{
            String sql = "INSERT INTO summarize  VALUES (?,?,?,? )";
            con = ConnectDb.connectDB();
            pst =con.prepareStatement(sql);
            pst.setString(1,value1);
            pst.setString(2,value2);
            pst.setString(3,value3);
            pst.setString(3,value4);
            pst.execute();
            System.out.println("INSERT CORRECT");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("INSERT FAIL");
        }

    }


    public void insertSpecialCar(){
        String value4 = "4";
        try{
            String sql = "INSERT INTO summarize  VALUES (?,?,?,? )";
            con = ConnectDb.connectDB();
            pst =con.prepareStatement(sql);
            pst.setString(1,value1);
            pst.setString(2,value2);
            pst.setString(3,value3);
            pst.setString(3,value4);
            pst.execute();
            System.out.println("INSERT CORRECT");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("INSERT FAIL");
        }

    }


}
