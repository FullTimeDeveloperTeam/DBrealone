package express.expressfxml;

import express.ConnectDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NameViewController implements Initializable {
    @FXML
    Button workBtn, outBtn, summaryBtn;
    @FXML
    TableView<DetailNameView> tableemp;
    @FXML
    TableColumn<DetailNameView, String> tableColumnId;

    @FXML
    TableColumn<DetailNameView, String> tableColumnDuty;

    @FXML
    TableColumn<DetailNameView, String> tableColumnBox;

    @FXML
    TableColumn<DetailNameView, String> tableColumnDate;
    @FXML
    Label label;
    @FXML
    ComboBox idCombo;
    //LoginViewController loginViewController ;
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    ObservableList<DetailNameView> observableList = FXCollections.observableArrayList();


    String test;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            showNameBtn();
            //showComboBox();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   /* public void showComboBox() {
        try {
            con = ConnectDb.connectDB();
            pst = con.prepareStatement("SELECT emp_id FROM employee");
            rs = pst.executeQuery();
            while (rs.next()) {
                idCombo.getItems().add(rs.getString("emp_id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public void workBtn(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(this.getClass().getResource("../expressfxml/WorkView.fxml").openStream());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException var6) {
            var6.printStackTrace();
        }
    }

    public void outBtn(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(this.getClass().getResource("../expressfxml/LoginView.fxml").openStream());
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

    public void showNameBtn() throws SQLException {
        String value = label.getText();
        try {
            con = ConnectDb.connectDB();
            rs = con.createStatement().executeQuery("SELECT emp_id ,work_duty,work_box,work_date FROM work_schedule where emp_id ='101'");
            while (rs.next()) {
                observableList.add(new DetailNameView(rs.getString("work_duty")
                        , rs.getString("work_box"), rs.getString("work_date")));

                String id = rs.getString("emp_id");
                String duty = rs.getString("work_duty");
                String box = rs.getString("work_box");
                String date = rs.getString("work_date");
                System.out.println(id);
                //System.out.println(value);
                //System.out.println("-----");
                System.out.println(test + "showname");
                //System.out.println(getTest());
                //System.out.println(value1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnDuty.setCellValueFactory(new PropertyValueFactory<>("duty"));
        tableColumnBox.setCellValueFactory(new PropertyValueFactory<>("box"));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableemp.setItems(null);
        tableemp.setItems(observableList);
    }


}
