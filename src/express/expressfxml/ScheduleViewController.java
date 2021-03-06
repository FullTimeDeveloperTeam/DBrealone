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

public class ScheduleViewController extends Component {
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
    @FXML Button logout;
    String value ="";
    String value1 = "";
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;

    ObservableList<DetailNameView> observableList = FXCollections.observableArrayList();
    ObservableList<DetailNameView> observableListTmp = FXCollections.observableArrayList();

    public void initialize() throws SQLException {
        showTableViewDefault();
//        showCombo();
    }

    public void showTableViewDefault() {
        observableListTmp.add(new DetailNameView("","",""));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableColumnBox.setCellValueFactory(new PropertyValueFactory<>("box"));
        tableColumnDuty.setCellValueFactory(new PropertyValueFactory<>("duty"));
        scheduleTable.setItems(null);
        scheduleTable.setItems(observableListTmp);
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
        JOptionPane optionPane = new JOptionPane ( );
        optionPane.setMessageType ( JOptionPane.QUESTION_MESSAGE );
        optionPane.setOptionType ( JOptionPane.YES_NO_OPTION );
        optionPane.setMessage ( "คุณต้องการ logout ใช่หรือไม่" );
        JDialog dialog = optionPane.createDialog ( null, "logout" );
        dialog.setVisible ( true );
        Integer selectedButton = ( Integer ) optionPane.getValue ( );
        if ( selectedButton == JOptionPane.YES_OPTION ){
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
        else if ( selectedButton == JOptionPane.NO_OPTION ){

        }

    }

    public void logoutBtn(ActionEvent event) throws IOException {
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
    private Boolean checkNum=false;
    public static boolean isNummeric(String str){
        return ((!str.equals("")))&&((str != null))&&(str.matches("^[0-9]{1,10}$"));//ต้องเป้น ตัวเลจ 10 ตัว
    }
    public void okBtn() {
        value = idText.getText();
        checkNum = isNummeric(idText.getText());
        /*if (idText.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,"กรุณากรอกข้อมูล","แจ้งเตือน",JOptionPane.INFORMATION_MESSAGE);
        }*/  if (!(checkNum) || idText.getText().isEmpty()) {
            alert();
        } else {
            clearTable();
            showSchedule();
        }
    }

    public void clearTable(){
        tableColumnDate.getTableView().getItems().clear();
        tableColumnDuty.getTableView().getItems().clear();
        tableColumnBox.getTableView().getItems().clear();
    }


    public void showSchedule()  {
        try {
            con = ConnectDb.connectDB();
            rs = con.createStatement().executeQuery("SELECT emp_id ,work_duty,work_box,work_date FROM work_schedule WHERE emp_id ='"+value+"' ORDER BY work_date");
            while (rs.next()) {
                observableList.add(new DetailNameView(rs.getString("work_duty"), rs.getString("work_box"), rs.getString("work_date")));
            }
            if ( observableList.isEmpty()){
                JOptionPane.showMessageDialog(this,"ไม่พบข้อมูล","แจ้งเตือน",JOptionPane.INFORMATION_MESSAGE);
            }
            System.out.println("SHOW CORRECT");
        } catch (SQLException e) {
                JOptionPane.showMessageDialog(this,"ไม่พบข้อมูล","แจ้งเตือน",JOptionPane.INFORMATION_MESSAGE);
            System.out.println("SHOW FAIL");
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

    public void alert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("");
        alert.setTitle("WARNING");
        alert.setContentText("Please fill out this form completely");
        alert.showAndWait();
    }

}
