
import java.sql.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQL_Bank_Server {

    public static Connection conn() {
        Connection con = null;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:bankdb.db");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return con;
    }

}

/*
 * import java.sql.*;
 * import java.util.logging.Level;
 * import java.util.logging.Logger;
 * 
 * public class SQL_Bank_Server {
 * Statement bankdb;
 * String useSQL = new String("Use henrybooks");
 * String output;
 * ResultSet results;
 * Statement sqlSt;
 * String SQL = "Select * From author order by authorlast";
 * 
 * try
 * {
 * 
 * Class.forName("com.mysql.jdbc.Driver");
 * String bankdb = "jdbc:mysql://localhost:3306/henrybooks";
 * Connection dbConnect = DriverManager.getConnection(bankdb, "user1",
 * "password1");
 * sqlSt = dbConnect.createStatement();
 * results = sqlSt.executeQuery(SQL);
 * while (results.next() != false) {
 * output = results.getString("AuthorFirst") + " " +
 * results.getString("AuthorLast");
 * System.out.println(output);
 * 
 * }
 * sqlSt.close();
 * 
 * }catch(
 * 
 * ClassNotFoundException ex)
 * {
 * Logger.getLogger(SQL_Bank_Server.getName()).log(Level.SEVERE, null, ex);
 * System.out.println("Class Not Found, Check The Jar");
 * 
 * }catch(
 * SQLExecption ex)
 * {
 * Logger.getLogger(SQL_Bank_Server.getName()).log(Level.SEVERE, null, ex);
 * System.out.println("SQL is Bad " + ex.getMessage());
 * 
 * }
 * }
 */