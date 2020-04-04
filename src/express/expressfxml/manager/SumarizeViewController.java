package express.expressfxml.manager;

import express.ConnectDb;
import express.expressfxml.DetailCar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SumarizeViewController {
    @FXML
    Button back;
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    @FXML TableView tableViewSum;
    @FXML TableColumn<DetailSummary,String> tableColumnDate;
    @FXML TableColumn<DetailSummary,String> tableColumnId;
    @FXML TableColumn<DetailSummary,String> tableColumnDuty;
    @FXML TableColumn<DetailSummary,String> tableColumnBox;
    @FXML TableColumn<DetailSummary,String> tableColumnSumVehicle;
    @FXML TableColumn<DetailSummary,String> tableColumnSumPrice;


    ArrayList<String> workId = new ArrayList<String>();

    public void initialize() throws SQLException {
        getWorkID();
        showTable();
    }

    public void getWorkID()throws SQLException{
        try{
            con = ConnectDb.connectDB();
            String sql = "SELECT DISTINCT  work_id as workid\n" +
                    "FROM summarize";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()){
                workId.add(rs.getString("workid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String i : workId) {
            System.out.println(i);
        }
    }


    ObservableList<DetailSummary> observableList = FXCollections.observableArrayList();

    public void showTable(){
        for (String value : workId){
            try{
                con = ConnectDb.connectDB();
                String sql = "SELECT t3.work_date as date_n ,t1.emp_id as id , t3.work_box as box ,t3.work_duty as duty, COUNT(t1.ticket_id) as count_ticket ,SUM(Price) as sum_ticket\n" +
                        "FROM summarize t1\n" +
                        "INNER JOIN ticket t2\n" +
                        "ON t1.ticket_id=t2.ticket_id\n" +
                        "INNER JOIN work_schedule t3\n" +
                        "ON t1.work_id= t3.work_id\n" +
                        "WHERE t1.work_id = '"+value+"'";
                ResultSet rs = con.createStatement().executeQuery(sql);
                while (rs.next()){
                    observableList.add(new DetailSummary(rs.getString("date_n"),rs.getString("id"),rs.getString("box"),rs.getString("duty"),rs.getString("count_ticket"),rs.getString("sum_ticket")));
                }
                System.out.println("SHOW CORRECT");
            } catch (Exception e) {
                System.out.println("SHOW FAIL");
                e.printStackTrace();
            }
            tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
            tableColumnBox.setCellValueFactory(new PropertyValueFactory<>("box"));
            tableColumnDuty.setCellValueFactory(new PropertyValueFactory<>("duty"));
            tableColumnSumVehicle.setCellValueFactory(new PropertyValueFactory<>("sumVehicle"));
            tableColumnSumPrice.setCellValueFactory(new PropertyValueFactory<>("sumPrice"));

            tableViewSum.setItems(null);
            tableViewSum.setItems(observableList);
        }

    }

    public  void backBtn(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        try{
            ((Node)event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(this.getClass().getResource("../manager/HomeView.fxml").openStream());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch ( IOException var6){
            var6.printStackTrace();
        }
    }
}
