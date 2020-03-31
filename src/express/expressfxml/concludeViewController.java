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
        String sql = "SELECT COUNT(Type_ticket) From ticket WHERE Type_ticket = '4Wheel'";
        try {
            con = ConnectDb.connectDB();
            pst = con.prepareStatement(sql);
            pst.execute();
            int text = rs.getInt(1);
            System.out.println(text);
            //fourWheel.setText(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
