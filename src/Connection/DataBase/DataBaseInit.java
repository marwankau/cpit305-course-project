package Connection.DataBase;

/* 
 * This Class is to create Players Table if it doesn't exist
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DataBaseInit {
    private final static String user = "root";
    private final static String pass = "12345";

    private static Connection conn;
    private static Statement stmt;

    public DataBaseInit(){
        try {
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/tictactoc", user, pass);
            Statement stmt = conn.createStatement();

            String query = "CREATE TABLE IF NOT EXISTS `players` (" +
            "`id` varchar(100) NOT NULL," +
            "`username` varchar(15) NOT NULL," +
            "`password` varchar(100) NOT NULL," +
            "`email` varchar(100) NOT NULL" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";

            stmt.execute(query);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
