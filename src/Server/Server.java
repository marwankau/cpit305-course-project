package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Server {
    static Connection conn;

    public static void main(String[] args) {

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:src/Server/Hospital.db");

            // Create server socket
            ServerSocket server = new ServerSocket(1999);

            System.out.println("Server waiting for connection...");

            while (true) {
                Socket socket = server.accept();
                new ClientHandler(socket, conn).start();

            }
        } catch (SQLException e) {
            System.out.println("Could not access database");
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}