package express.expressfxml.manager;

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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SumarizeViewController {
    @FXML
    Button back;
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;

    @FXML
    TableView tableViewSum;
    @FXML
    TableColumn tableColumnDate;
    @FXML TableColumn tableColumnId;
    @FXML TableColumn tableColumnDuty;
    @FXML TableColumn tableColumnBox;
    @FXML TableColumn tableColumnFour;
    @FXML TableColumn tableColumnSix;
    @FXML TableColumn tableColumnTen;
    @FXML TableColumn tableColumnSpecial;
    @FXML TableColumn tableColumnSum;

    public  void backBtn(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        try{
            ((Node)event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(this.getClass().getResource("../manager/HomeView.fxml").openStream());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch ( IOException var6){
            var6.printStackTrace();
        }
    }
    ObservableList<DetailSummary> observableList = FXCollections.observableArrayList();
    public void showTableSum(){
            String fourWheel ="SELECT COUNT(Type_ticket),SUM(Price)\n" +
                    "FROM summarize t1\n" +
                    "INNER JOIN ticket t2 \n" +
                    "ON t1.ticket_id=t2.ticket_id\n" +
                    "WHERE t2.Type_ticket ='4wheel'";
            String sixWheel = "SELECT COUNT(Type_ticket),SUM(Price)\n" +
                    "FROM summarize t1\n" +
                    "INNER JOIN ticket t2 \n" +
                    "ON t1.ticket_id=t2.ticket_id\n" +
                    "WHERE t2.Type_ticket ='6wheel'";
            String tenWheel = "SELECT COUNT(Type_ticket),SUM(Price)\n" +
                    "FROM summarize t1\n" +
                    "INNER JOIN ticket t2 \n" +
                    "ON t1.ticket_id=t2.ticket_id\n" +
                    "WHERE t2.Type_ticket ='10wheel'";
        try {

            con = ConnectDb.connectDB();
            String sql = "";
            pst = con.prepareStatement(sql);
            pst.execute();
            //observableList.add(new DetailSummary(rs.getString("work_date"),rs.getString("emp_id"),rs.getString("emp_duty")))



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
