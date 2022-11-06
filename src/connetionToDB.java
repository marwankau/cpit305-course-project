import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteConnection;


public class connetionToDB {
    public static void main(String[] args) {
        try  {
            Connection conn = (Connection) DriverManager.getConnection("jdbc:sqlite:C:/Users/kheda/Desktop/mysqllite");
           // Statement stmt = (Statement) conn.createStatement();
           Statement stmt = (Statement) conn.createStatement();
           ResultSet result = stmt.executeQuery("SELECT * FROM books b WHERE  isAvailable =1");
           while (result.next()) {
            int id = result.getInt("book_id");
            String name = result.getString("book_name");
            int isa=result.getInt("isAvailable");
            String sec = result.getString("bookSec");
      
            System.out.printf("%10d  %20s  %20s  %20s\n", id, name, sec,isa);
      
          }
      
          conn.close();
        
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e);
        }
    }
}
