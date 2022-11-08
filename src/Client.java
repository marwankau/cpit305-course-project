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
        // arrays that contains login information to enter server
        String ReceptionUserName[] = { "RakanSalama", "MoathAlSolami", "AbdulrahmanNahfawi" };
        String ReceptionPassword[] = { "RakanS", "MoathS", "AbdulrahmanN" };
        String ReceptionName[] = { "Rakan", "Moath", "Abdulrahman" };

        Boolean check = true; // For the while loop, If it change the loop will stop ( it will only change if
                              // the user did login)
        while (check) { // this loop is made because if the receptionist didn't enter a correct username
                        // or password, it will ask him again and again.

            // Here we take Username And password
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter username:");
            String ScannerUsername = scanner.next();
            System.out.print("Enter Password:");
            String ScannerPassword = scanner.next();

            int i = 0; // to reset the i , so it can recheck again V
            for (i = 0; i < 3; i++) { // it will check all usernames and passwords

                if (ReceptionUserName[i].equals(ScannerUsername) && ReceptionPassword[i].equals(ScannerPassword)) {
                    connect(ReceptionName, i, scanner); // connects the reception with the server
                    check = false; // to get out from while loop
                }
            }
            if (check == false) { // it will skip the wrong username and password and get out from the loop.
                continue;
            }
            System.out.println("Username or Password is wrong");
        }
    }

    public static void connect(String ReceptionName[], int i, Scanner scanner) throws IOException, SQLException {
        String line;
        String choice;

        try {
            Socket client = new Socket("localhost", 2000); // it will connect the receptionist with the server
            // -----------------------------------------------------------------------------------------------------------
            InputStream in = client.getInputStream(); // Read bytes from server
            OutputStream out = client.getOutputStream(); // Send bytes to server // Read and write between server and
                                                         // receptionist
            Scanner sc = new Scanner(System.in);
            Scanner receiver = new Scanner(in); // Read normally
            PrintWriter writer = new PrintWriter(out, true); // write normally
            // -----------------------------------------------------------------------------------------------------------
            writer.println("( " + ReceptionName[i] + " ) " + "is connected\n"); // This will send a notfication to the
                                                                                // server whom did join
            ArrayList<String> inp = new ArrayList<String>();
            inp.add("choose: ");
            inp.add("Which room do you want to update?");
            inp.add("Update visitor name");
            inp.add("Enter check in date DD MM: ");
            inp.add("Enter check out date DD MM: ");
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
/*
 * while (true) {
 * System.out.println("i am waitingXDDDDDDDDDDDDD");
 * while (receiver.hasNextLine()) {
 * line = receiver.nextLine();
 * System.out.println(line);
 * if (inp.contains(line)) {
 * choice = sc.nextLine();
 * writer.println(choice);
 * }
 * if(receiver.nextLine().equals("")){
 * break;
 * }
 * }
 * }
 */