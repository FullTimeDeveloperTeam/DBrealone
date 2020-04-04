package express.expressfxml;

import express.ConnectDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleViewController {
    @FXML
    TableView<DetailNameView> scheduleTable;
    @FXML
    TableColumn<DetailNameView, String> tableColumnDate;

    @FXML
    TableColumn<DetailNameView, String> tableColumnDuty;
    @FXML
    TableColumn<DetailNameView, String> tableColumnBox;

    @FXML
    Button backBtn, workBtn, summaryBtn;
    @FXML
    ComboBox idCombo;
    @FXML
    TextField idText;

    String value ="";
    String value1 = "";
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;

    public void initialize() throws SQLException {

        showCombo();
    }
    public void workBtn(ActionEvent event) {
        Stage primaryStage = new Stage();
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(this.getClass().getResource("../expressfxml/WorkView.fxml").openStream());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void outBtn(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(this.getClass().getResource("../expressfxml/FirstView.fxml").openStream());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException var6) {
            var6.printStackTrace();
        }
    }

    public void summaryBtn(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(this.getClass().getResource("../expressfxml/concludeView.fxml").openStream());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException var6) {
            var6.printStackTrace();
        }
    }

    public void okBtn(){
        value = idText.getText();
        clearTable();
        showSchedule();
    }

    public void clearTable(){
        tableColumnDate.getTableView().getItems().clear();
        tableColumnDuty.getTableView().getItems().clear();
        tableColumnBox.getTableView().getItems().clear();
    }


    ObservableList<DetailNameView> observableList = FXCollections.observableArrayList();

    public void showSchedule()  {
        try {
            con = ConnectDb.connectDB();
            rs = con.createStatement().executeQuery("SELECT emp_id ,work_duty,work_box,work_date FROM work_schedule WHERE emp_id ='"+value+"'");
            while (rs.next()) {
                observableList.add(new DetailNameView(rs.getString("work_duty"), rs.getString("work_box"), rs.getString("work_date")));

            }
            String id = rs.getString("emp_id");
            String duty = rs.getString("work_duty");
            String box = rs.getString("work_box");
            String date = rs.getString("work_date");


        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableColumnBox.setCellValueFactory(new PropertyValueFactory<>("box"));
        tableColumnDuty.setCellValueFactory(new PropertyValueFactory<>("duty"));

        scheduleTable.setItems(null);
        scheduleTable.setItems(observableList);


    }

    public void showCombo(){
        try{
            con = ConnectDb.connectDB();
            pst = con.prepareStatement("SELECT emp_id FROM employee");
            rs = pst.executeQuery();
            while (rs.next()){
                idCombo.getItems().add(rs.getString("emp_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
