import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        // 1. Create server socket
        ServerSocket server = new ServerSocket(2000);

        System.out.println("Server waiting for connection...");
        System.out.println("Welcome to server");
        try {
            while (true) {
                Menu m = new Menu();
                String line;
                Socket client = server.accept();

                InputStream in = client.getInputStream();
                OutputStream out = client.getOutputStream();

                Scanner receiver = new Scanner(in);
                PrintWriter writer = new PrintWriter(out, true);


                line = receiver.nextLine();// get the msg from the client
                System.out.printf(line);// ... is connected  

                

                writer.println(m.welcome);

                receiver.close();
                client.close();

            }

        } catch (SocketException e) {// if the client didn't send anything
            System.out.println("Client Didn't send anything");
        }
        server.close();
    }
}