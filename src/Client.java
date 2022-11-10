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
        String ClientInput;
            // -----------------------------------------------------------------------------------------------------------
        try {
            Socket client = new Socket("localhost", 2000); // it will connect the client with the server
            // -----------------------------------------------------------------------------------------------------------
            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();
            Scanner sc = new Scanner(System.in);                                 
            Scanner receiver = new Scanner(in);
            PrintWriter writer = new PrintWriter(out, true);
            // -----------------------------------------------------------------------------------------------------------
            ArrayList<String> inp = new ArrayList<String>();
            inp.add("Enter username:");
            inp.add("Enter Password:");
            inp.add("choose: ");
            inp.add("Which room do you want to update?");      // This arrayList is a stop sign.
            inp.add("Enter check in date DD/MM: ");            //If the server sent one of these things
            inp.add("Update visitor name");                    //it will stop receiving and it will send input to the server.
            inp.add("Enter check out date DD/MM: ");
            inp.add("ENTER ROOM NO: ");
            // -----------------------------------------------------------------------------------------------------------
            while (true) {
                line = receiver.nextLine();
                System.out.println(line);
                if (inp.contains(line)) {                               // In this loop, it will receive from the server and it will print it. 
                    ClientInput = sc.nextLine();                        // After that, it will check if the thing that have been sent was one of the 
                    writer.println(ClientInput);                        // Stop signs, if it was. It will stop to send an input to the server.
                }
            }

        } catch (ConnectException e) { // if the server was offline it will handel it
            System.out.println("Server is offline");
        }
    }
}