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
        ServerSocket server = new ServerSocket(2000);

        System.out.println("Server is Online");
        try {
            while (true) {
                Menu m = new Menu();
                String line;

                Socket client = server.accept();
                //---------------------------------------------------------------------
                InputStream in = client.getInputStream();
                OutputStream out = client.getOutputStream();
                Scanner receiver = new Scanner(in);                         //read and write betwen server and receptionist
                PrintWriter writer = new PrintWriter(out, true);
                //---------------------------------------------------------------------
                line = receiver.nextLine();// get the msg from the client
                System.out.print(line);// ... is connected
                System.out.println();

                //writer.println(m.welcome);


                
                receiver.close();
                client.close();
                writer.close();
            }

        } catch (SocketException e) {// if the client didn't send anything
            System.out.println("Client Didn't send anything");
        }
        server.close();
    }
}