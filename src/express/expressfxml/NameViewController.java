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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NameViewController {
    @FXML
    Button workBtn, outBtn,summaryBtn;
    @FXML
    TableView<DetailNameView> tableemp;
    @FXML
    TableColumn<DetailNameView,String> tableColumnName;

    @FXML TableColumn<DetailNameView,String> tableColumnDuty;

    @FXML TableColumn<DetailNameView,String> tableColumnBox;

    @FXML TableColumn<DetailNameView,String> tableColumnDate;


    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    ObservableList<DetailNameView> observableList = FXCollections.observableArrayList();


    public  void initialize() throws SQLException {
        showNameBtn();
    }


    public void workBtn(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        try{
            ((Node)event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(this.getClass().getResource("../expressfxml/WorkSelectView.fxml").openStream());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch ( IOException var6){
            var6.printStackTrace();
        }
    }

    public void outBtn(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        try{
            ((Node)event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(this.getClass().getResource("../expressfxml/LoginView.fxml").openStream());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch ( IOException var6){
            var6.printStackTrace();
        }
    }

    public void summaryBtn(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        try{
            ((Node)event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(this.getClass().getResource("../expressfxml/concludeView.fxml").openStream());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch ( IOException var6){
            var6.printStackTrace();
        }
    }

    public void showNameBtn() throws SQLException {
        try {
            con = ConnectDb.connectDB();
            rs = con.createStatement().executeQuery("SELECT emp_name,emp_duty,emp_box,emp_date FROM employee");
            while (rs.next()){
                observableList.add(new DetailNameView(rs.getString("emp_name"),rs.getString("emp_duty")
                        ,rs.getString("emp_box"),rs.getString("emp_date")));

                String name = rs.getString("emp_name");
                String duty = rs.getString("emp_duty");
                String box = rs.getString("emp_box");
                String date = rs.getString("emp_date");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnDuty.setCellValueFactory(new PropertyValueFactory<>("duty"));
        tableColumnBox.setCellValueFactory(new PropertyValueFactory<>("box"));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableemp.setItems(null);
        tableemp.setItems(observableList);
    }
}
