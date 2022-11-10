package Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Server {
    static Connection conn;
    public static void main(String[] args) throws IOException {
        
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:src/Hospital.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 1. Create server socket
        ServerSocket server = new ServerSocket(1999);

        System.out.println("Server waiting for connection...");

        while (true) {
            Socket socket = server.accept();
            new ClientHandler(socket, conn).start();

        }
    }
}