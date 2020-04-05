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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class concludeViewController extends Component {
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;

    @FXML
    DatePicker dateemp;
    @FXML
    TextField idemp;
    @FXML
    Button back,confirmBtn;

    @FXML
    Label fourWheel,sixWheel,tenWheel,Special,ticketFour,ticketSix,ticketTen
            ,ticketSpecial,priceFour,priceSix,priceTen,priceSum,ticketAll;

    String value ="";
    String value1 = "";
    String value2 = "";
    String value3 = "";
   /* double amount = Double.parseDouble();
    DecimalFormat formatter = new DecimalFormat("#,###.00");*/
    public void initialize() throws SQLException{
        showLabelDefault();
    }

    private Boolean checkNum=false;
    public static boolean isNummeric(String str){
        return ((!str.equals("")))&&((str != null))&&(str.matches("^[0-9]{1,10}$"));//ต้องเป้น ตัวเลจ 10 ตัว
    }

    public void showAllLabel() throws SQLException {
        checkNum = isNummeric(idemp.getText());
        if (!(checkNum) || idemp.getText().isEmpty() || dateemp.getEditor().getText().isEmpty()){
            alert();
        }else{
            value2 = idemp.getText();
            value = dateemp.getValue().toString();
            value3 = getWorkID();
            showLabelFour();
            showLabelSix();
            showLabelTen();
            showLabelSpecial();
            showLabelPriceSum();
            showLabelTicketAll();
        }
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
            if ( value3.isEmpty()){
                JOptionPane.showMessageDialog(this,"ไม่พบข้อมูล","แจ้งเตือน",JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"ไม่พบข้อมูล","แจ้งเตือน",JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
        }
        return value3;
    }


    public void showLabelDefault() {
        fourWheel.setText("0");
        ticketFour.setText("0");
        sixWheel.setText("0");
        Special.setText("0");
        ticketSix.setText("0");
        tenWheel.setText("0");
        ticketTen.setText("0");
        priceSum.setText("0");
        ticketAll.setText("0");
        ticketSpecial.setText("0");
        priceFour.setText("0");
        priceSix.setText("0");
        priceTen.setText("0");
    }

    public void showLabelFour() {
        String sql = "SELECT COUNT(t1.ticket_id) as count_ticket ,SUM(Price) as sum_ticket \n" +
                "FROM summarize t1\n" +
                "INNER JOIN ticket t2 \n" +
                "ON t1.ticket_id=t2.ticket_id\n" +
                "WHERE t1.work_id = '"+value3+"' AND t1.ticket_id = '1'";
        try {
            con = ConnectDb.connectDB();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()){
                fourWheel.setText(String.valueOf(rs.getInt("count_ticket")));
                ticketFour.setText(String.valueOf(rs.getInt("count_ticket")));
                priceFour.setText(String.valueOf(rs.getInt("sum_ticket")));
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
                "WHERE t1.work_id = '"+value3+"' AND t1.ticket_id = '2'";
        try{
            con = ConnectDb.connectDB();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()){
                sixWheel.setText(String.valueOf(rs.getInt("count_ticket")));
                ticketSix.setText(String.valueOf(rs.getInt("count_ticket")));
                priceSix.setText(String.valueOf(rs.getInt("sum_ticket")));
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
                "WHERE t1.work_id = '"+value3+"' AND t1.ticket_id = '3'";
        try {
            con = ConnectDb.connectDB();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()){
                tenWheel.setText(String.valueOf(rs.getInt("count_ticket")));
                ticketTen.setText(String.valueOf(rs.getInt("count_ticket")));
                priceTen.setText(String.valueOf(rs.getInt("sum_ticket")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showLabelSpecial(){
        String sql = "SELECT COUNT(t1.ticket_id) as count_ticket ,SUM(Price) as sum_ticket \n" +
                "FROM summarize t1\n" +
                "INNER JOIN ticket t2 \n" +
                "ON t1.ticket_id=t2.ticket_id\n" +
                "WHERE t1.work_id = '"+value3+"' AND t1.ticket_id = '4'";
        try {
            con = ConnectDb.connectDB();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()){
                Special.setText(String.valueOf(rs.getInt("count_ticket")));
                ticketSpecial.setText(String.valueOf(rs.getInt("count_ticket")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void showLabelPriceSum(){
        String sql = "SELECT COUNT(t1.ticket_id) as count_ticket ,SUM(Price) as sum_ticket \n" +
                "FROM summarize t1\n" +
                "INNER JOIN ticket t2 \n" +
                "ON t1.ticket_id=t2.ticket_id\n" +
                "WHERE t1.work_id = '"+value3+"' ";
        try {
            con = ConnectDb.connectDB();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()){
                //double amount = Double.parseDouble(priceSum.getText(rs.getString("sum_ticket")));
                String sum = rs.getString("sum_ticket");
                double amount = Double.parseDouble(sum);
                DecimalFormat formatter = new DecimalFormat("#,###.00");
                priceSum.setText(formatter.format(amount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showLabelTicketAll(){
        String sql = "SELECT COUNT(t1.ticket_id) as count_ticket ,SUM(Price) as sum_ticket \n" +
                "FROM summarize t1\n" +
                "INNER JOIN ticket t2 \n" +
                "ON t1.ticket_id=t2.ticket_id\n" +
                "WHERE t1.work_id = '"+value3+"'";
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

    public void backBtn(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        try{
            ((Node)event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(this.getClass().getResource("../expressfxml/scheduleView.fxml").openStream());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch ( IOException var6){
            var6.printStackTrace();
        }
    }

    /*public void showCombo(){
        try {
            con = ConnectDb.connectDB();
            pst = con.prepareStatement("SELECT emp_id FROM employee");
            rs = pst.executeQuery();
            while (rs.next()){
                idemp.getItems().add(rs.getString("emp_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public void alert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("");
        alert.setTitle("WARNING");
        alert.setContentText("Please fill out this form completely");
        alert.showAndWait();
    }

}
