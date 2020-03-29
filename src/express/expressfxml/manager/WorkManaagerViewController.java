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
    Button insert, back, finish;
    @FXML
    TextField nameText, idText;

    @FXML ComboBox idCombo,idCombo1;
    @FXML ChoiceBox dutyChoice, boxChoice;
    @FXML DatePicker dateChoice;


    @FXML TableView<DetailEmployee> tableEmp;
    @FXML TableColumn<DetailEmployee,String> tableColEmp_id;
    @FXML TableColumn<DetailEmployee,String> tableColEmp_name;
    @FXML TableColumn<DetailEmployee,String> tableColEmp_duty;
    @FXML TableColumn<DetailEmployee,String> tableColEmp_box;
    @FXML TableColumn<DetailEmployee,String> tableColEmp_date;

    ObservableList<DetailEmployee> observableList = FXCollections.observableArrayList();

    public void initialize() throws SQLException {
        showTable();
        showemp_nameToCombo();
        showChoice();
    }


    public void showTable() throws SQLException {
        try {
            con = ConnectDb.connectDB();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM employee");
            while (rs.next()) {
                observableList.add(new DetailEmployee(rs.getString("emp_id"), rs.getString("emp_name"), rs.getString("emp_duty")
                        , rs.getString("emp_box"), rs.getString("emp_date")
                ));
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

    public void updateBtn(ActionEvent event)throws SQLException{
        if (dutyChoice.getSelectionModel().isEmpty() || boxChoice.getSelectionModel().isEmpty() || dateChoice.getEditor().getText().isEmpty() || idCombo.getSelectionModel().isEmpty()){
            alert();
        }else{
            String value1 = dutyChoice.getValue().toString();
            String value2 = boxChoice.getValue().toString();
            String value3 = dateChoice.getValue().toString();
            String value4 = idCombo.getValue().toString();
            try{
                String sql = "UPDATE employee SET emp_duty= '"+value1+"',emp_box='"+value2+"',emp_date='"+value3+"' WHERE  emp_id = '"+value4+"'";
                con = ConnectDb.connectDB();
                pst =con.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(this, "แก้ไขข้อมูลเสร็จสิ้น", "บันทึก", 1);
                System.out.println("UPDATE CORRECT");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("UPDATE FAIL");
            }
            clearChoice();
            clearTable();
            showTable();
        }
    }

    public void clearBtn(ActionEvent event){
        clearChoice();
    }

    public void insertBtn(ActionEvent event) throws SQLException {
        if (idText.getText().isEmpty() || nameText.getText().isEmpty()){
            alert();
        }else {
            try{
                String sql = "INSERT INTO employee  VALUES (?,?,null,null,null )";
                con = ConnectDb.connectDB();
                pst =con.prepareStatement(sql);
                pst.setString(1,idText.getText());
                pst.setString(2,nameText.getText());
                pst.execute();
                clearChoice();
                JOptionPane.showMessageDialog(this, "เพิ่มข้อมูลเสร็จสิ้น", "บันทึก", 1);
                System.out.println("INSERT CORRECT");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "กรอกข้อมูลให้ถูกรูปแบบ", "WARNING", 1);
                System.out.println("INSERT FAIL");
            }
            nameText.setText("");
            idText.setText("");
            idCombo.getItems().clear();
            idCombo1.getItems().clear();
            clearTable();
            showTable();
            showemp_nameToCombo();
        }

    }

    public void deleteBtn(ActionEvent event) throws IOException, SQLException {
        if (idCombo1.getSelectionModel().isEmpty()){
            alert();
        }else {
            String value1 = idCombo1.getValue().toString();
            try{
                String sql = "DELETE FROM employee WHERE emp_id= '"+value1+"'";
                con = ConnectDb.connectDB();
                pst =con.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(this, "ลบข้อมูลเสร็จสิ้น", "บันทึก", 1);
                System.out.println("DELETE CORRECT");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("DELETE FAIL");
            }
            idCombo.getItems().clear();
            idCombo1.getItems().clear();
            clearTable();
            showTable();
            showemp_nameToCombo();
        }
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

    public void showChoice(){
        dutyChoice.getItems().add(0,"Morning");
        dutyChoice.getItems().add(1,"Evening");
        boxChoice.getItems().add(0,"1");
        boxChoice.getItems().add(1,"2");
    }

    public void showemp_nameToCombo(){
        try {
            con = ConnectDb.connectDB();
            pst = con.prepareStatement("SELECT emp_id FROM employee");
            rs = pst.executeQuery();
            while (rs.next()){
                idCombo.getItems().add(rs.getString("emp_id"));
                idCombo1.getItems().add(rs.getString("emp_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearTable(){
        tableColEmp_id.getTableView().getItems().clear();
        tableColEmp_name.getTableView().getItems().clear();
        tableColEmp_duty.getTableView().getItems().clear();
        tableColEmp_box.getTableView().getItems().clear();
        tableColEmp_date.getTableView().getItems().clear();

    }
    public void clearChoice(){
        idCombo.getItems().clear();
        idCombo1.getItems().clear();
        dutyChoice.getItems().clear();
        boxChoice.getItems().clear();
        dateChoice.getEditor().clear();
        nameText.setText("");
        idText.setText("");
        showemp_nameToCombo();
        showChoice();
    }

    public void alert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("");
        alert.setTitle("WARNING");
        alert.setContentText("Please fill out this form completely");
        alert.showAndWait();
    }

}
