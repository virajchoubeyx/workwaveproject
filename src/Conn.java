import javax.swing.*;
import java.sql.*;

public class Conn {
    Connection connection = null;
    Statement statement;

    public Conn(){
//  constructor
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/workwave","root","Viraj@2212"
            );
            statement = connection.createStatement();
            System.out.println("connection successful!");

        }catch (Exception abc){
//      --
            JOptionPane.showMessageDialog(null,"exception");
            System.out.println(abc);

        }
    }

}
