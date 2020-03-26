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
    @FXML
    Button confirm, back, finish;
    @FXML
    TextField name, id;
    @FXML
    ComboBox duty, box, time;
    @FXML
    TableView tableemp;
    @FXML
    TableColumn tableemp_id, tableemp_name, tableemp_duty, tableemp_box, tableemp_time;
    ObservableList<String> listemp = FXCollections.observableArrayList();
    ObservableList<DetailEmployee> observableList = FXCollections.observableArrayList();


    public void intitialize() {
        show();

    }

    public void showempToCombo() {
        try {
            con = ConnectDb.connectDB();
            String sql = "SELECT * FROM employee";
            pst = con.prepareStatement(sql); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void show(ActionEvent event) {
        String sql = "SELECT * FROM employee";
        try {
            con = ConnectDb.connectDB();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                observableList.add(new DetailEmployee(rs.getString("emp_id"), rs.getString("emp_name"),
                        rs.getString("emp_duty"), rs.getString("emp_box"), rs.getString("emp_time")));
                System.out.println("SHOW COMPLETE");
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SHOW FAIL");
        }
        System.out.println("\n");
        tableemp_id.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        tableemp_name.setCellValueFactory(new PropertyValueFactory<>("emp_name"));
        tableemp_duty.setCellValueFactory(new PropertyValueFactory<>("emp_duty"));
        tableemp_box.setCellValueFactory(new PropertyValueFactory<>("emp_box"));
        tableemp_box.setCellValueFactory(new PropertyValueFactory<>("emp_time"));


        tableemp.setItems(null);
        tableemp.setItems(observableList);

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
