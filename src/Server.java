import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.mariadb.jdbc.Connection;

public class Server {
    public static void main(String[] args) throws IOException, SQLException {

        Connection con = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/rooms", "root","2037276");
        Statement stmt = con.createStatement();
        try {
            stmt.executeQuery("INSERT INTO rooms VALUES (1,'single',NULL,NULL,NULL,1),(2,'double',NULL,NULL,NULL,1),(3,'double',NULL,NULL,NULL,1),(4,'triple',NULL,NULL,NULL,1),(5,'double',NULL,NULL,NULL,1),(6,'triple',NULL,NULL,NULL,1);");
        } catch (SQLException e) {
        } // Insert only once when server starts
        ServerSocket server = new ServerSocket(2000);
        System.out.println("Server is Online");
        while (true) {
            Socket client = server.accept();

            MyThread thread = new MyThread(client,con);
            thread.start();
        }
    }
}