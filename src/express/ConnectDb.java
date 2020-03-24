package express;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDb {
    public static Connection connectDB(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/expressway?useTimezone=true&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url,"root","");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
