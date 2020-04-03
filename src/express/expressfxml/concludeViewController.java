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
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class concludeViewController {
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;


    @FXML
    Button back;
    @FXML
    Label fourWheel,sixWheel,tenWheel,ticketFour,ticketSix,ticketTen,priceSum,ticketAll;


    public void initialize(){
        showLabelFour();
        showLabelSix();
        showLabelTen();
        showLabelPriceSum();
        showLabelTicketAll();
    }
    public void backBtn(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        try{
            ((Node)event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(this.getClass().getResource("../expressfxml/NameView.fxml").openStream());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch ( IOException var6){
            var6.printStackTrace();
        }
    }

    ObservableList observableList = FXCollections.observableArrayList();
    public void showLabelFour() {
        String sql = "SELECT COUNT(t1.ticket_id) as count_ticket ,SUM(Price) as sum_ticket \n" +
                "FROM summarize t1\n" +
                "INNER JOIN ticket t2 \n" +
                "ON t1.ticket_id=t2.ticket_id\n" +
                "WHERE t2.Type_ticket ='4wheel'";
        try {
            con = ConnectDb.connectDB();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()){
                fourWheel.setText(String.valueOf(rs.getInt("count_ticket")));
                ticketFour.setText(String.valueOf(rs.getInt("count_ticket")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showLabelSix(){
        String sql = "SELECT COUNT(t1.ticket_id) as count_ticket ,SUM(Price) as sum_ticket \n" +
                "FROM summarize t1\n" +
                "INNER JOIN ticket t2 \n" +
                "ON t1.ticket_id=t2.ticket_id\n" +
                "WHERE t2.Type_ticket ='6wheel'";
        try{
            con = ConnectDb.connectDB();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()){
                sixWheel.setText(String.valueOf(rs.getInt("count_ticket")));
                ticketSix.setText(String.valueOf(rs.getInt("count_ticket")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showLabelTen(){
        String sql = "SELECT COUNT(t1.ticket_id) as count_ticket ,SUM(Price) as sum_ticket \n" +
                "FROM summarize t1\n" +
                "INNER JOIN ticket t2 \n" +
                "ON t1.ticket_id=t2.ticket_id\n" +
                "WHERE t2.Type_ticket ='10wheel'" ;
        try {
            con = ConnectDb.connectDB();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()){
                tenWheel.setText(String.valueOf(rs.getInt("count_ticket")));
                ticketTen.setText(String.valueOf(rs.getInt("count_ticket")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void showLabelPriceSum(){
        String sql = "SELECT COUNT(t1.ticket_id) as count_ticket ,SUM(Price) as sum_ticket \n" +
                "FROM summarize t1\n" +
                "INNER JOIN ticket t2 \n" +
                "ON t1.ticket_id=t2.ticket_id\n";
        try {
            con = ConnectDb.connectDB();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()){
                priceSum.setText(String.valueOf(rs.getInt("sum_ticket")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showLabelTicketAll(){
        String sql = "SELECT COUNT(t1.ticket_id) as count_ticket ,SUM(Price) as sum_ticket \n" +
                "FROM summarize t1\n" +
                "INNER JOIN ticket t2 \n" +
                "ON t1.ticket_id=t2.ticket_id\n";
        try {
            con = ConnectDb.connectDB();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()){
                ticketAll.setText(String.valueOf(rs.getInt("count_ticket")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//
}
