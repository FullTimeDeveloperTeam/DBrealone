package express.expressfxml.manager;

import express.ConnectDb;
import express.expressfxml.DetailEmployee;
import express.expressfxml.DetailWorkEmployee;
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
    TextField nameText, idText,phoneText, workidText;

    @FXML ComboBox idCombo,idCombo1;
    @FXML ChoiceBox dutyChoice, boxChoice;
    @FXML DatePicker dateChoice;


    @FXML TableView<DetailWorkEmployee> tableWork;
    @FXML TableColumn<DetailWorkEmployee,String> tableColWork_id;
    @FXML TableColumn<DetailWorkEmployee,String> tableColWorkemp_id;
    @FXML TableColumn<DetailWorkEmployee,String> tableColWork_name;
    @FXML TableColumn<DetailWorkEmployee,String> tableColWork_duty;
    @FXML TableColumn<DetailWorkEmployee,String> tableColWork_box;
    @FXML TableColumn<DetailWorkEmployee,String> tableColWork_date;

    @FXML TableView<DetailEmployee> tableEmp;
    @FXML TableColumn<DetailEmployee,String> tableColEmp_id;
    @FXML TableColumn<DetailEmployee,String> tableColEmp_name;
    @FXML TableColumn<DetailEmployee,String> tableColEmp_phone;

    ObservableList<DetailWorkEmployee> observableList = FXCollections.observableArrayList();
    ObservableList<DetailEmployee> observableListEmp = FXCollections.observableArrayList();

    public void initialize() throws SQLException {
        showEmpTable();
        showWorkTable();
        showemp_nameToCombo();
        showChoice();
    }

    public void showEmpTable() throws SQLException {
        try {
            con = ConnectDb.connectDB();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `employee`");
            while (rs.next()) {
                observableListEmp.add(new DetailEmployee(rs.getString("emp_id"),rs.getString("emp_name"),rs.getString("emp_phone")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SHOW FAIL");
        }

        tableColEmp_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColEmp_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColEmp_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        tableEmp.setItems(null);
        tableEmp.setItems(observableListEmp);

    }

    public void showWorkTable() throws SQLException {
        try {
            con = ConnectDb.connectDB();
            ResultSet rs = con.createStatement().executeQuery("SELECT work_schedule.work_id,work_schedule.emp_id, employee.emp_name," +
                    "work_schedule.work_duty,work_schedule.work_box,work_schedule.work_date FROM work_schedule " +
                    "INNER JOIN employee ON work_schedule.emp_id = employee.emp_id");
            while (rs.next()) {
                observableList.add(new DetailWorkEmployee(rs.getString("work_id"),rs.getString("emp_id"), rs.getString("emp_name"), rs.getString("work_duty")
                        , rs.getString("work_box"), rs.getString("work_date")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SHOW FAIL");
        }

        tableColWork_id.setCellValueFactory(new PropertyValueFactory<>("work_id"));
        tableColWorkemp_id.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        tableColWork_name.setCellValueFactory(new PropertyValueFactory<>("emp_name"));
        tableColWork_duty.setCellValueFactory(new PropertyValueFactory<>("work_duty"));
        tableColWork_box.setCellValueFactory(new PropertyValueFactory<>("work_box"));
        tableColWork_date.setCellValueFactory(new PropertyValueFactory<>("work_date"));

        tableWork.setItems(null);
        tableWork.setItems(observableList);

    }

    public static boolean isNummeric(String str){
        return ((!str.equals("")))&&((str != null))&&(str.matches("^[0-9]{10}$"));//ต้องเป้น ตัวเลจ 10 ตัว
    }

    public void insertEmpBtn(ActionEvent event) throws SQLException {
        Boolean id = isNummeric(idText.getText());
        Boolean num =isNummeric(phoneText.getText());
        if (!(num) || !(id) || idText.getText().isEmpty() || nameText.getText().isEmpty() || phoneText.getText().isEmpty()){
            alert();
        }else {
            try{
                String sql = "INSERT INTO employee  VALUES (?,?,? )";
                con = ConnectDb.connectDB();
                pst =con.prepareStatement(sql);
                pst.setString(1,idText.getText());
                pst.setString(2,nameText.getText());
                pst.setString(3,phoneText.getText());
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
            phoneText.setText("");
            idCombo.getItems().clear();
            idCombo1.getItems().clear();
            clearTable();
            showemp_nameToCombo();
            showEmpTable();
            showWorkTable();
        }
    }

    public void updateEmpBtn(ActionEvent event)throws SQLException{
        if (idText.getText().isEmpty() || nameText.getText().isEmpty() || phoneText.getText().isEmpty()){
            alert();
        }else{
            String value =  idText.getText();
            String value1 = nameText.getText();
            String value2 = phoneText.getText();
            try{
                String sql = "UPDATE employee SET emp_name= '"+value1+"',emp_phone='"+value2+"' WHERE  emp_id = '"+value+"'";
                con = ConnectDb.connectDB();
                pst =con.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(this, "แก้ไขข้อมูลเสร็จสิ้น", "บันทึก", 1);
                System.out.println("UPDATE CORRECT");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("UPDATE FAIL");
            }
            nameText.setText("");
            idText.setText("");
            phoneText.setText("");
            idCombo.getItems().clear();
            idCombo1.getItems().clear();
            showemp_nameToCombo();
            clearTable();
            showEmpTable();
            showWorkTable();
        }
    }

    public void deleteEmpBtn(ActionEvent event) throws IOException, SQLException {
        if (idCombo1.getSelectionModel().isEmpty()){
            alert();
        }else {
            String value1 = idCombo1.getValue().toString();
            try{
                String sql = "DELETE employee,work_schedule FROM employee INNER JOIN work_schedule ON employee.emp_id = work_schedule.emp_id WHERE employee.emp_id= '"+value1+"'";
                con = ConnectDb.connectDB();
                pst =con.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(this, "ลบข้อมูลเสร็จสิ้น", "บันทึก", 1);
                System.out.println(value1);
                System.out.println("DELETE CORRECT");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("DELETE FAIL");
            }
            idCombo.getItems().clear();
            idCombo1.getItems().clear();
            clearTable();
            showemp_nameToCombo();
            showEmpTable();
            showWorkTable();
        }
    }

    public void clearEmpBtn(ActionEvent event){
        nameText.setText("");
        idText.setText("");
        phoneText.setText("");
        idCombo.getItems().clear();
        idCombo1.getItems().clear();
        showemp_nameToCombo();
    }

    public void clearWorkBtn(ActionEvent event){
        clearChoice();
    }

    public void clearChoice(){
        idCombo.getItems().clear();
        idCombo1.getItems().clear();
        dutyChoice.getItems().clear();
        boxChoice.getItems().clear();
        dateChoice.getEditor().clear();
        workidText.setText("");
        showemp_nameToCombo();
        showChoice();
    }


    public void updateWorkBtn(ActionEvent event)throws SQLException{
        if (workidText.getText().isEmpty()||dutyChoice.getSelectionModel().isEmpty() || boxChoice.getSelectionModel().isEmpty()
                || dateChoice.getEditor().getText().isEmpty() || idCombo.getSelectionModel().isEmpty()){
            alert();
        }else{
            String value =  workidText.getText();
            String value1 = dutyChoice.getValue().toString();
            String value2 = boxChoice.getValue().toString();
            String value3 = dateChoice.getValue().toString();
            String value4 = idCombo.getValue().toString();
            try{
                String sql = "UPDATE work_schedule SET work_duty= '"+value1+"',work_box='"+value2+"',work_date='"+value3+"',emp_id='"+value4+"' WHERE  work_id = '"+value+"'";
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
            showEmpTable();
            showWorkTable();
        }
    }

    public void insertWorkBtn(ActionEvent event) throws SQLException {
        if (workidText.getText().isEmpty()||dutyChoice.getSelectionModel().isEmpty() || boxChoice.getSelectionModel().isEmpty()
                || dateChoice.getEditor().getText().isEmpty() || idCombo.getSelectionModel().isEmpty()){
            alert();
        }else {
            try{
                String sql = "INSERT INTO work_schedule  VALUES (?,?,?,?,? )";
                con = ConnectDb.connectDB();
                pst =con.prepareStatement(sql);
                pst.setString(1,workidText.getText());
                pst.setString(2,dutyChoice.getValue().toString());
                pst.setString(3,boxChoice.getValue().toString());
                pst.setString(4,dateChoice.getValue().toString());
                pst.setString(5,idCombo.getValue().toString());
                pst.execute();
                JOptionPane.showMessageDialog(this, "เพิ่มข้อมูลเสร็จสิ้น", "บันทึก", 1);
                System.out.println("INSERT CORRECT");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "กรอกข้อมูลให้ถูกรูปแบบ", "WARNING", 1);
                System.out.println("INSERT FAIL");
            }
            clearChoice();
            clearTable();
            showEmpTable();
            showWorkTable();
        }
    }


    public void deleteWorkBtn(ActionEvent event) throws IOException, SQLException {
        if (workidText.getText().isEmpty()){
            alert();
        }else {
            String value1 = workidText.getText();
            try{
                String sql = "DELETE work_schedule FROM work_schedule  WHERE work_id= '"+value1+"'";
                con = ConnectDb.connectDB();
                pst =con.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(this, "ลบข้อมูลเสร็จสิ้น", "บันทึก", 1);
                System.out.println("DELETE CORRECT");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("DELETE FAIL");
            }
            workidText.setText("");
            clearTable();
            showEmpTable();
            showWorkTable();
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
        dutyChoice.getItems().add(1,"Night");
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
        tableColEmp_phone.getTableView().getItems().clear();
        tableColWork_id.getTableView().getItems().clear();
        tableColWorkemp_id.getTableView().getItems().clear();
        tableColWork_name.getTableView().getItems().clear();
        tableColWork_duty.getTableView().getItems().clear();
        tableColWork_box.getTableView().getItems().clear();
        tableColWork_date.getTableView().getItems().clear();
    }


    public void alert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("");
        alert.setTitle("WARNING");
        alert.setContentText("Please fill out this form completely");
        alert.showAndWait();
    }

}
