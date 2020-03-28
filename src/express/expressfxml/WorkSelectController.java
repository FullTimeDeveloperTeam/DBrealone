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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class WorkSelectController {
    @FXML
    Button confirm;
    @FXML
    DatePicker date;
    @FXML
    ComboBox<String> boxCombo;
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private boolean checkbox =false;
    private boolean checktime =false;
    ObservableList<String> observableList = FXCollections.observableArrayList();

    public void initialize() {
        showemp_boxToCombo();
        boxCombo.setItems(observableList);

    }

    public void conBtn(ActionEvent event) throws SQLException {
        LocalDate value = date.getValue();
        String value1 = boxCombo.getValue();


        try {
            con = ConnectDb.connectDB();
            String sql = "SELECT emp_time,emp_box FROM employee WHERE emp_time = '" + value+"' AND emp_box = '"+value1+"'";
            //ResultSet rs = con.createStatement().executeQuery(sql);
            pst = con.prepareStatement(sql);
            pst.execute();
            //value.compareTo(rs.getString("emp_time"));
            //value1.compareTo(rs.getString("emp_box"));
            checkbox = true;
            value.equals(rs.getString("emp_time"));
            value1.equals(rs.getString("emp_box"));
            //getItems().equals(rs.getString("emp_box"));
            System.out.println("CORRECT");
            System.out.println(sql);
            System.out.println(pst);
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

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR");

            System.out.println(date.getValue());
            System.out.println(rs.getString("emp_time"));
            System.out.println(boxCombo.getValue());
            System.out.println(pst);
        }
    }

    public void showemp_boxToCombo() {
        try {
            con = ConnectDb.connectDB();
            String sql = "SELECT emp_box FROM employee";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                //listemp.add(rs.getString("emp_duty"));
                observableList.add(rs.getString("emp_box"));
                //listemp.add(rs.getString("emp_time"));
                System.out.println("show COMBO BOX");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
