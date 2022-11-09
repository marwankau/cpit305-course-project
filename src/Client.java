import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException, SQLException {
        String line;
        String choice;

        try {
            Socket client = new Socket("localhost", 2000); // it will connect the receptionist with the server
            // -----------------------------------------------------------------------------------------------------------
            InputStream in = client.getInputStream(); // Read bytes from server
            OutputStream out = client.getOutputStream(); // Send bytes to server // Read and write between server and receptionist
                                                         
            Scanner sc = new Scanner(System.in);
            Scanner receiver = new Scanner(in); // Read normally
            PrintWriter writer = new PrintWriter(out, true); // write normally
            // -----------------------------------------------------------------------------------------------------------
            ArrayList<String> inp = new ArrayList<String>();
            inp.add("Enter username:");
            inp.add("Enter Password:");
            inp.add("choose: ");
            inp.add("Which room do you want to update?");
            inp.add("Update visitor name");
            inp.add("Enter check in date DD/MM: ");
            inp.add("Enter check out date DD/MM: ");
            inp.add("ENTER ROOM NO: ");

            while (true) {
                line = receiver.nextLine();
                System.out.println(line);
                if (inp.contains(line)) {
                    choice = sc.nextLine();
                    writer.println(choice);
                }
            }

        } catch (ConnectException e) { // if the server was offline it will handel it
            System.out.println("Server is offline");
            
        }
    }
}