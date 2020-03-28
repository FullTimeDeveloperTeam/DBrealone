package express.expressfxml.manager;

import express.ConnectDb;
import express.expressfxml.DetailEmployee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkManaagerViewController extends Component {
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    String e_id ="";
    @FXML
    Button insert, back, finish;
    @FXML
    TextField nameText, idText,dutyText, boxText, dateText;

    @FXML TableView<DetailEmployee> tableEmp;
    @FXML TableColumn<DetailEmployee,String> tableColEmp_id;
    @FXML TableColumn<DetailEmployee,String> tableColEmp_name;
    @FXML TableColumn<DetailEmployee,String> tableColEmp_duty;
    @FXML TableColumn<DetailEmployee,String> tableColEmp_box;
    @FXML TableColumn<DetailEmployee,String> tableColEmp_date;

//    @FXML TableView<DetailEmployee> tableemp;
//    @FXML TableColumn<DetailEmployee,String> tableColumnemp_id;
//    @FXML TableColumn<DetailEmployee,String> tableColumnemp_name;
//    @FXML TableColumn<DetailEmployee,String> tableColumnemp_duty;
//    @FXML TableColumn<DetailEmployee,String> tableColumnemp_box;
//    @FXML TableColumn<DetailEmployee,String> tableCoulumnemp_date;


    //ObservableList<String> listemp = FXCollections.observableArrayList();

    ObservableList<DetailEmployee> observableList = FXCollections.observableArrayList();


    public void initialize() throws SQLException {
        showTable();
       // showemp_dutyToCombo();
       // showemp_boxToCombo();
       // showemp_timeToCombo();
       // duty.setItems(listempduty);
        //box.setItems(listempbox);
        //time.setItems(listemp);
    }

    ObservableList<String> listempduty = FXCollections.observableArrayList();

    /*public void showemp_dutyToCombo() {
        try {
            con = ConnectDb.connectDB();
            String sql = "SELECT emp_duty FROM employee";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                listempduty.add(rs.getString("emp_duty"));
               // listemp.add(rs.getString("emp_box"));
                //listemp.add(rs.getString("emp_time"));
                System.out.println("show COMBO DUTY");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
    ObservableList<String> listempbox = FXCollections.observableArrayList();

    /*public void showemp_boxToCombo() {
        try {
            con = ConnectDb.connectDB();
            String sql = "SELECT emp_box FROM employee";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                //listemp.add(rs.getString("emp_duty"));
                listempbox.add(rs.getString("emp_box"));
                //listemp.add(rs.getString("emp_time"));
                System.out.println("show COMBO BOX");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
   /* public void showemp_timeToCombo() {
        try {
            con = ConnectDb.connectDB();
            String sql = "SELECT emp_time    FROM employee";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                //listemp.add(rs.getString("emp_duty"));
                //listemp.add(rs.getString("emp_box"));
                listemp.add(rs.getString("emp_time"));
                System.out.println("show COMBO TIME");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public void showTable() throws SQLException {
        try {
            con = ConnectDb.connectDB();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM employee");
            while (rs.next()) {
                observableList.add(new DetailEmployee(rs.getString("emp_id"), rs.getString("emp_name"), rs.getString("emp_duty")
                        , rs.getString("emp_box"), rs.getString("emp_date")
                ));
                System.out.println("SHOW COMPLETE");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SHOW FAIL");
        }

        tableColEmp_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColEmp_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColEmp_duty.setCellValueFactory(new PropertyValueFactory<>("duty"));
        tableColEmp_box.setCellValueFactory(new PropertyValueFactory<>("box"));
        tableColEmp_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableEmp.setItems(null);
        tableEmp.setItems(observableList);

    }


    public void insertBtn(ActionEvent event) throws SQLException {
        try{
            String sql = "INSERT INTO employee(emp_id,emp_name,emp_duty,emp_box,emp_date) VALUES (?,?,?,?,?)";
            con = ConnectDb.connectDB();
            pst =con.prepareStatement(sql);
            pst.setString(1,idText.getText());
            pst.setString(2,nameText.getText());
            pst.setString(3,dutyText.getText());
            pst.setString(4,boxText.getText());
            pst.setString(5,dateText.getText());
            pst.execute();
            System.out.println("INSERT CORRECT");


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("INSERT MAIDAI");
        }
        nameText.setText("");
        idText.setText("");
        dutyText.setText("");
        boxText.setText("");
        dateText.setText("");
        showTable();
    }
    public void backBtn(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(this.getClass().getResource("../manager/HomeView.fxml").openStream());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException var6) {
            var6.printStackTrace();
        }
    }

    public void confirmBtn(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        try {
            JOptionPane.showMessageDialog(this, "กรอกข้อมูลเสร็จสิ้น", "บันทึก", 1);
            ((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(this.getClass().getResource("../manager/HomeView.fxml").openStream());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException var6) {
            var6.printStackTrace();
        }
    }


}
