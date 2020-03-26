package express.expressfxml.manager;

import express.ConnectDb;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegisterViewController {
    @FXML
    Button back;
    @FXML
    TextField username,password,fname,lname,phone;

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private Boolean checkTextUser=false;
    private Boolean checkTextName=false;
    private Boolean checkTextLastName=false;
    private Boolean checkPassword=false;
    private Boolean checkNum=false;
    private int checkRepeat=0;

    public static boolean isString(String str){
        return ((!str.equals("")))&&((str != null))&&(str.matches("^[a-zA-Z0-9]{3,20}$"));
    }
    public static boolean isPassword(String str){
        return ((!str.equals("")))&&((str != null))&&(str.matches("[a-zA-Z0-9]{1,10}$"));
    }

    public static boolean isNummeric(String str){
        return ((!str.equals("")))&&((str != null))&&(str.matches("^[0-9]{10}$"));
    }

    public void clear(){
        username.setText("");
        password.setText("");
        fname.setText("");
        lname.setText("");
        phone.setText("");
    }

    public void backBtn(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        try{
            ((Node)event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(this.getClass().getResource("../manager/LoginManagerView.fxml").openStream());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch ( IOException var6){
            var6.printStackTrace();
        }
    }

    public void confirmBtn(ActionEvent event) {
        checkTextUser=isString(username.getText());
        checkTextName=isString(fname.getText());
        checkTextLastName=isString(lname.getText());
        checkNum=isNummeric(phone.getText());
        checkPassword=isPassword(password.getText());
        System.out.println(checkNum);
        try {
            con = ConnectDb.connectDB();
            if (username.getText().isEmpty() || password.getText().isEmpty() || fname.getText().isEmpty() || lname.getText().isEmpty() ||
            phone.getText().isEmpty()){
                //joption
                System.out.println("Text not OK");
            }
            else if (!(checkTextUser && checkTextName && checkTextLastName && checkPassword && checkNum)){ //เช็คว่า เป็นตัวอีกษทั้งหมดไม่มี ตัวเลขและตัวอักษรพิเศษ
                //joption
                System.out.println("not OK");
            }else if(checkRepeat !=0) {
                System.out.println("OK");
                System.out.println(checkRepeat);
        }
            else {
                String sql ="INSERT INTO user(User_name,password,firstname,lastname,phone)"
                        +"VALUES (?,?,?,?,?)";
                pst =con.prepareStatement(sql);
                System.out.println("Try register");

                pst.setString(1,username.getText());
                pst.setString(2,password.getText());
                pst.setString(3,fname.getText());
                pst.setString(4,lname.getText());
                pst.setString(5,phone.getText());

                pst.execute();
                //joption
                clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
