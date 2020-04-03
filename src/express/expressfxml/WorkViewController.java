package express.expressfxml;

import express.ConnectDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    String value1 = "";
    String value2 = "101";
    String value3 = getValue();


    ObservableList<DetailCar> observableList = FXCollections.observableArrayList();

    public void initialize() throws SQLException {

        System.out.println(value3);

    }

    public WorkViewController() throws SQLException {
    }

    public String getValue()throws SQLException{
//        String value = dateAddTable.getValue().toString();
        String value = "2020-04-01";
        try{
            con = ConnectDb.connectDB();
            String sql = "SELECT work_id \n" +
                    "FROM work_schedule \n" +
                    "WHERE work_date='"+value+"' AND emp_id='"+value2+"'";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()){
                value3 = rs.getString("work_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return value3;
    }
    public void confirmBtn(ActionEvent event) throws IOException {
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

    public void backBtn(ActionEvent event) throws IOException {
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

    public void showTableCar(){
        String value = dateAddTable.getValue().toString();
        try{
            con = ConnectDb.connectDB();
            String sql = "SELECT t2.Type_ticket as type, t2.Price as cost,t3.work_date as date_n \n" +
                    "FROM summarize t1\n" +
                    "INNER JOIN ticket t2 \n" +
                    "ON t1.ticket_id=t2.ticket_id\n"+
                    "INNER JOIN work_schedule t3\n"+
                    "ON t1.work_id=t3.work_id\n"+
                    "WHERE ";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()){
                observableList.add(new DetailCar(rs.getString("date_n"),rs.getString("type"),rs.getString("cost")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void insertFourCar(ActionEvent event) throws SQLException {
        value3 = getValue();
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
