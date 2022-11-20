
import java.sql.*;

public class SQL_Bank_Server {

    public static Connection conn() {
        Connection con = null;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(
                    "jdbc:sqlite:X:/Documents/Programming/Java/cpit305-Group-Project-3/cpit305-course-project/BankServer.db");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return con;
    }

}