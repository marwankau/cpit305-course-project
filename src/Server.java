import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.mariadb.jdbc.Connection;

public class Server {
    public static void main(String[] args) throws IOException {
        try {
            Connection con = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/rooms", "root","2037276"); // You must change the password and user
                Statement stmt = con.createStatement();
                ResultSet result = stmt.executeQuery("SELECT * FROM rooms;");
                if (!result.next()) { // If the database is empty, it will insert these values. If the database was already implemented it will ignore it.             
                    stmt.executeQuery("INSERT INTO rooms VALUES (1,'single',NULL,NULL,NULL,1),(2,'double',NULL,NULL,NULL,1),(3,'double',NULL,NULL,NULL,1),(4,'triple',NULL,NULL,NULL,1),(5,'double',NULL,NULL,NULL,1),(6,'triple',NULL,NULL,NULL,1);");
                }
            // --------------------------------------------------------------------------------------------------------------------------
            ServerSocket server = new ServerSocket(2000); // Server creation
            System.out.println("Server is Online");

            while (true) {
                // For threads, it will accept the client request and make a thread for that client
                Socket client = server.accept();
                MyThread thread = new MyThread(client, con);
                thread.start();
            }
        } catch (Exception e) {
            System.out.println("ERROR !!"); // Mainly this msg will apear if the we run the server more than one time
        }
    }
}