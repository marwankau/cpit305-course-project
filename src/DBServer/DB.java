package DBServer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static String url = "jdbc:sqlite:mydata";     
    private static Connection conn;
    public static Connection getConnection()  {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            // log an exception. fro example:
            System.out.println("Failed to create the database connection."); 
        }
        catch(ClassNotFoundException e){

        }
        return conn;
    }
}
