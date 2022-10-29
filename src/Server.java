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
                String line;
                int choice;
                Socket client = server.accept();
                //---------------------------------------------------------------------
                InputStream in = client.getInputStream();
                OutputStream out = client.getOutputStream();
                Scanner receiver = new Scanner(in);                         //read and write betwen server and receptionist
                PrintWriter writer = new PrintWriter(out, true);
                Menu m = new Menu(writer);
                //---------------------------------------------------------------------
                line = receiver.nextLine();// get the msg from the client
                System.out.println(line);// ... is connected                  //give an information about who is connected to the server
                //---------------------------------------------------------------------
                m.MainMenu();                                               // sending Mainmenu to the receptionist
                //---------------------------------------------------------------------
                //choice = receiver.nextInt();
                //System.out.println(choice);        
                writer.close();
            }
        } catch (SocketException e) {// if the client didn't send anything
            System.out.println("Client Didn't send anything");
        }
        server.close();
    }
}