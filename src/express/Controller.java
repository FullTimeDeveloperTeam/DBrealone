package express;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

    public class Controller extends Component {
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    public ConnectDb connectDb;
    @FXML
    TableView<Detail> model;
    @FXML
    TableColumn<Detail, String> tableColumnid;
    @FXML
    TableColumn<Detail, String> tableColumnname;
    @FXML
    Button clear, show;
    @FXML
    Button update;
    @FXML
    Button delete;
    @FXML
    Button save,search;
    @FXML
    TextField idtxt;
    @FXML
    TextField nametxt;
    @FXML
    ComboBox empID_combo;

    ObservableList<String> listEmployee = FXCollections.observableArrayList();
    public void showEmployeeToCombo() {
        try {
            con = ConnectDb.connectDB();
            String sql = "select * from employee";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                listEmployee.add(rs.getString("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTableEmployee(){
        try {
            String value1= (String) empID_combo.getValue();
            String value2 = nametxt.getText();


            String sql="update employee set id='"+value1+"' , name='"+value2+"' where id='"+value1+"'";
            pst=con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(this,"แก้ไขข้อมูลเสร็จสิ้น","บันทึก",JOptionPane.INFORMATION_MESSAGE);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        showBtn();
    }
    //class ที่ใช้คำสั่งตั้งเเต่เริ่ม run
    public void initialize(){
        showBtn();
        showEmployeeToCombo();
        empID_combo.setItems(listEmployee);
    }


    ObservableList<Detail> observableList = FXCollections.observableArrayList();
    public void showBtn() {
        String sql = "select * from employee";
        try {
            con = ConnectDb.connectDB();
            ResultSet rs = con.createStatement().executeQuery(sql);
            observableList.clear();
            while (rs.next()) {
                observableList.add(new Detail(rs.getString("id"), rs.getString("name")));

                System.out.println("Show success");

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("show fail");
        }
        System.out.println("\n");
        tableColumnid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnname.setCellValueFactory(new PropertyValueFactory<>("name"));


        model.setItems(null);

        model.setItems(observableList);

    }


    public void insertBtn() {


        try {
            String sql = "INSERT INTO employee( id, name)VALUES (?,?)";
            con = ConnectDb.connectDB();
            pst=con.prepareStatement(sql);
            //Statement stm = connection.createStatement();
            //stm.executeUpdate(sql);
            pst.setString(1,idtxt.getText());
            pst.setString(2,nametxt.getText());
            pst.execute();
            System.out.println("success");
            JOptionPane.showMessageDialog(this,"บันทึกเสร็จสิ้น","",JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("test insert");
        }
        nametxt.setText("");
        idtxt.setText("");
        showBtn();
    }

    public boolean CheckBox1(){
        if (idtxt.getText().equals("")){
            return false;
        }else{
            return true;
        }
    }

    public boolean CheckBox2(){
        if (nametxt.getText().equals("")){
            return false;
        }else{
            return true;
        }
    }

    public void SearchBtn() {
        String value1 = idtxt.getText();
        String value2 = nametxt.getText();
        String sql = "SELECT * FROM  employee WHERE id LIKE '%" + value1 + "%'";
        String sql2 = "SELECT * FROM  employee WHERE name LIKE '%" + value2 + "%'";
//        String sql = "select id"+" From employee "+"WHERE id LIKE '%" + value1 + "%'";
//        String sql2 = "select name"+" From employee "+"WHERE name LIKE '%" + value2 + "%'";


        try {
            con = ConnectDb.connectDB();
            ResultSet rs = con.createStatement().executeQuery("select * from employee");
            pst = con.prepareStatement(sql);
            pst.execute();
            ResultSet rsid = con.createStatement().executeQuery("SELECT * FROM  employee WHERE id LIKE '%" + value1 + "%'");
            ResultSet rsname = con.createStatement().executeQuery("SELECT * FROM  employee WHERE name LIKE '%" + value2 + "%'");

            while (rs.next()) {
                System.out.println(pst.execute());
                System.out.println(value2);
                System.out.println(value1);
                System.out.println(CheckBox2());
                System.out.println(CheckBox1());
                if (CheckBox1()) {
                    while (rsid.next()) {
                        observableList.clear();
                        observableList.add(new Detail(rsid.getString("id"), rsid.getString("name")));
                        System.out.println("Show success1");
                    }
                }
                else if (CheckBox2() && rsname != null){
                    while (rsname.next()) {
                        observableList.clear();
                        observableList.add(new Detail(rsname.getString("id"), rsname.getString("name")));
                        System.out.println("Show success1");
                    }
                }else {
                    JOptionPane.showMessageDialog(this,"no search data","Search",JOptionPane.INFORMATION_MESSAGE);
                }
//                observableList.clear();
//                observableList.add(new Detail(rsid.getString("id"), rsid.getString("name")));
//                   System.out.println("Show success1");

//                if (idtxt.getText().equals(rsid)) {
//                    observableList.add(new Detail(rs.getString("id"), rs.getString("name")));
//                    System.out.println("Show success1");
//                }
//                if (nametxt.getText().equals(rsname)) {
//                    observableList.add(new Detail(rs.getString("id"), rs.getString("name")));
//                    System.out.println("Show success2");
//                } else {
//                    JOptionPane.showMessageDialog(this,"no search data","Search",JOptionPane.INFORMATION_MESSAGE);
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("show fail");
        }
        tableColumnid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnname.setCellValueFactory(new PropertyValueFactory<>("name"));
    }
    public void  deleteBtn(){
        try {
            String value1= (String) empID_combo.getValue();
            String value2= nametxt.getText();
            con =ConnectDb.connectDB();
            String sql="delete From employee where id ='"+value1+"' ";
            pst=con.prepareStatement(sql);
            pst.execute();
            System.out.println("success");
            JOptionPane.showMessageDialog(this,"ลบข้อมูลเสร็จสิ้น","คำเตือน",JOptionPane.INFORMATION_MESSAGE);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("no ");

        }
        showBtn();
    }
    public void clearBtn(){
        nametxt.setText("");
        idtxt.setText("");
        tableColumnid.setCellValueFactory(new PropertyValueFactory<>(""));
        tableColumnname.setCellValueFactory(new PropertyValueFactory<>(""));

        ObservableList<Detail> AllDetails;
        AllDetails= model.getItems();
        AllDetails.forEach(AllDetails::remove);
    }

//    public static boolean isString(String str){
//        return ((!str.equals("")))&&((str != null))&&(str.matches("^[a-zA-Z]*$"));
//    }
//
//    public static boolean isNummeric(String str){
//        return ((!str.equals("")))&&((str != null))&&(str.matches("^[0-9]{10}$"));
//    }
}
