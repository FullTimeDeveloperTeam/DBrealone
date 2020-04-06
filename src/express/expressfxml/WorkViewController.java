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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

public class WorkViewController extends Component {
    @FXML Button okBtn, backBtn,fourWheel,sixWheel,tenWheel,specialWheel;
    @FXML
    DatePicker dateAddTable;
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;

    String value ="";
    String value1 = "";
    String value2 = "";
    String value3 = "";
    @FXML
    TextField empIdTextField;
    @FXML TableView<DetailCar> tableWork;
    @FXML TableColumn<DetailCar,String> tableColID;
    @FXML TableColumn<DetailCar,String> tableColDate;
    @FXML TableColumn<DetailCar,String> tableColType;
    @FXML TableColumn<DetailCar,String> tableColPrice;


    ObservableList<DetailCar> observableList = FXCollections.observableArrayList();
    ObservableList<DetailCar> observableListTmp = FXCollections.observableArrayList();

    public void initialize() throws SQLException {
        showTableViewDefault();
    }

    public void showTableViewDefault() {
        observableListTmp.add(new DetailCar("","","",""));
        tableColID.setCellValueFactory(new PropertyValueFactory<>("countCar"));
        tableColDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableColType.setCellValueFactory(new PropertyValueFactory<>("ticket"));
        tableColPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableWork.setItems(null);
        tableWork.setItems(observableListTmp);
    }

    public String getWorkID()throws SQLException{
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

    public String getSpaceID()throws SQLException{
        int nummax = 0;
        try{
            con = ConnectDb.connectDB();
            String sql = "SELECT Max(space_id) as maxspace \n" +
                    "FROM summarize";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()){
                nummax = rs.getInt("maxspace");
                nummax++;
                value1 = String.valueOf(nummax);
            }
//            nummax++;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value1;
    }

    private Boolean checkNum=false;
    public static boolean isNummeric(String str){
        return ((!str.equals("")))&&((str != null))&&(str.matches("^[0-9]{1,10}$"));//ต้องเป้น ตัวเลจ 10 ตัว
    }

    public void oKBtn(){
        checkNum = isNummeric(empIdTextField.getText());
        if(!(checkNum) ||empIdTextField.getText().isEmpty() || dateAddTable.getEditor().getText().isEmpty()){
            alert();
        }else {
            value2 = empIdTextField.getText();
            value = dateAddTable.getValue().toString();
            clearTable();
            showTableCar();
        }
    }

    public void showTableCar(){
        int countCar = 0;
        try{
            con = ConnectDb.connectDB();
            String sql = "SELECT t2.Type_ticket as ticket, t2.Price as price,t3.work_date as date_n \n" +
                    "                  FROM summarize t1\n" +
                    "                    INNER JOIN ticket t2 \n" +
                    "                    ON t1.ticket_id=t2.ticket_id\n" +
                    "                    INNER JOIN work_schedule t3\n" +
                    "                    ON t1.work_id=t3.work_id\n" +
                    "                   WHERE t3.work_date='"+value+"' AND t3.emp_id='"+value2+"' ";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()){
                countCar++;
                observableList.add(new DetailCar(String.valueOf(countCar),rs.getString("date_n"),rs.getString("ticket"),rs.getString("price")));
            }
            if ( observableList.isEmpty()){
                JOptionPane.showMessageDialog(this,"เริ่มการทำงาน","แจ้งเตือน",JOptionPane.INFORMATION_MESSAGE);
            }
            System.out.println("SHOW CORRECT");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"ไม่พบข้อมูล","แจ้งเตือน",JOptionPane.INFORMATION_MESSAGE);
            System.out.println("SHOW FAIL");
            e.printStackTrace();
        }

        tableColID.setCellValueFactory(new PropertyValueFactory<>("countCar"));
        tableColDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableColType.setCellValueFactory(new PropertyValueFactory<>("ticket"));
        tableColPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableWork.setItems(null);
        tableWork.setItems(observableList);
    }

    public void clearTable(){
        tableColID.getTableView().getItems().clear();
        tableColDate.getTableView().getItems().clear();
        tableColType.getTableView().getItems().clear();
        tableColPrice.getTableView().getItems().clear();
    }

    public void insertFourCar() throws SQLException {
        value1 = getSpaceID();
        value3 = getWorkID();
        String value4 = "1";
        try{
            String sql = "INSERT INTO summarize (space_id,emp_id,work_id,ticket_id) VALUES (?,?,?,'1' )";
            con = ConnectDb.connectDB();
            pst =con.prepareStatement(sql);

            pst.setString(1,value1);
            pst.setString(2,value2);
            pst.setString(3,value3);

            pst.execute();
            System.out.println("INSERT CORRECT");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("INSERT FAIL");
            System.out.println(value1+" 1");
            System.out.println(value2+" 2");
            System.out.println(value3+" 3");
            System.out.println(value4+" 4");
        }
        clearTable();
        showTableCar();
    }

    public void insertSixCar() throws SQLException {
        value1 = getSpaceID();
        value3 = getWorkID();
        String value4 = "2";
        try{
            String sql = "INSERT INTO summarize  VALUES (?,?,?,'2' )";
            con = ConnectDb.connectDB();
            pst =con.prepareStatement(sql);
            pst.setString(1,value1);
            pst.setString(2,value2);
            pst.setString(3,value3);

            pst.execute();
            System.out.println("INSERT CORRECT");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("INSERT FAIL");
        }
        clearTable();
        showTableCar();
    }

    public void insertTenCar() throws SQLException {
        value1 = getSpaceID();
        value3 = getWorkID();
        String value4 = "3";
        try{
            String sql = "INSERT INTO summarize  VALUES (?,?,?,'3' )";
            con = ConnectDb.connectDB();
            pst =con.prepareStatement(sql);
            pst.setString(1,value1);
            pst.setString(2,value2);
            pst.setString(3,value3);

            pst.execute();
            System.out.println("INSERT CORRECT");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("INSERT FAIL");
        }
        clearTable();
        showTableCar();

    }

    public void insertSpecialCar() throws SQLException {
        value1 = getSpaceID();
        value3 = getWorkID();
        String value4 = "4";
        try{
            String sql = "INSERT INTO summarize (space_id,emp_id,work_id,ticket_id) VALUES (?,?,?,'4' )";
            con = ConnectDb.connectDB();
            pst =con.prepareStatement(sql);
            pst.setString(1,value1);
            pst.setString(2,value2);
            pst.setString(3,value3);

            pst.execute();
            System.out.println("INSERT CORRECT");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("INSERT FAIL");
        }
        clearTable();
        showTableCar();
    }


    public void confirmBtn(ActionEvent event) throws IOException {
        JOptionPane.showMessageDialog(this,"ทำงานเสร็จสิ้น","แจ้งเตือน",JOptionPane.INFORMATION_MESSAGE);
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

    public void alert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("");
        alert.setTitle("WARNING");
        alert.setContentText("Please fill out this form completely");
        alert.showAndWait();
    }


}
