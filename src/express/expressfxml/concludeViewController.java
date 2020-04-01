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
        String sql = "SELECT COUNT(Type_ticket) as count_ticket From ticket WHERE Type_ticket ='4wheel'";
        try {
            con = ConnectDb.connectDB();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()){
                System.out.println(rs.getInt("count_ticket"));
                fourWheel.setText(String.valueOf(rs.getInt("count_ticket")));
            }
//            int text = rs.getInt(1);
            System.out.println("gg");
            System.out.println("gg");
//            System.out.println(text);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void showLabelSix(){
        String sql = "SELECT COUNT(Type_ticket) as count_ticket From ticket WHERE Type_ticket ='6wheel'";
        try{
            con = ConnectDb.connectDB();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()){
                sixWheel.setText(String.valueOf(rs.getInt("count_ticket")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showLabelTen(){
        String sql = "SELECT";
        try {
            con = ConnectDb.connectDB();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()){
                tenWheel.setText(String.valueOf(rs.getInt("count_ticket")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showLabelTicketFour(){
        String sql = "";
        try {
            con = ConnectDb.connectDB();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()){
                ticketFour.setText(String.valueOf(rs.getInt("count_ticket")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showLabelTicketSix(){
        String sql = "";
        try {
            con = ConnectDb.connectDB();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()){
                ticketSix.setText(String.valueOf(rs.getInt("count_ticket")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void showLabelTicketTen(){
        String sql = "";//เชี้ยแอบมองกูไอสัส
        try {
            con = ConnectDb.connectDB();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()){
                ticketTen.setText(String.valueOf(rs.getInt("count_ticket")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showLabelPriceSum(){
        String sql = "";
        try {
            con = ConnectDb.connectDB();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()){
                priceSum.setText(String.valueOf(rs.getInt("")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showLabelTicketAll(){
        String sql = "";
        try {
            con = ConnectDb.connectDB();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()){
                ticketAll.setText(String.valueOf(rs.getInt("")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
