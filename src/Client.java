import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        // arrays that contains login information to enter server
        String ReceptionUserName[] = { "RakanSalama", "MoathAlSolami", "AbdulrahmanNahfawi" };
        String ReceptionPassword[] = { "RakanS", "MoathS", "AbdulrahmanN" };
        String ReceptionName[] = { "Rakan", "Moath", "Abdulrahman"};

        // Here we take Username And password
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username:");
        String ScannerUsername = scanner.next();
        System.out.print("Enter Password:");
        String ScannerPassword = scanner.next();
        // After we took the username and password we will compare it with the username
        // and password in the system, and with the username and password that the
        // receptionist entered
        // if it is true, the system will connnect the recepetionist with the server, if
        // not it will give a msg and exit
        for (int i = 0; i < 4; i++) {
            try {
                if (ReceptionUserName[i].equals(ScannerUsername) && ReceptionPassword[i].equals(ScannerPassword)) {
                    try {
                        Socket client = new Socket("localhost", 2000); // Here it will connects the client with the server
                        InputStream in = client.getInputStream();
                        OutputStream out = client.getOutputStream();

                        Scanner receiver = new Scanner(in);
                        PrintWriter writer = new PrintWriter(out, true);
                        writer.println("( " + ReceptionName[i] + " ) " + "is connected\n");
                        String line;
                        line = receiver.nextLine();
                        System.out.printf("server msg: %s\n", line);
                        Scanner keyboard = new Scanner(System.in);
                        line = keyboard.nextLine();
                        //line = "Client msg :" + line ;
                        writer.println(line);

                        
                        keyboard.close();
                        receiver.close();
                        client.close();
                    } catch (ConnectException e) {// if the server is off it will catch this exception
                        System.out.println("The server is off");
                    }
                    
                    break; // to ignore the below catch
                }
            } catch (ArrayIndexOutOfBoundsException e) { // it will catch it if the user or password is wrong
                System.out.println("Username or password wrong");
            }
            scanner.close();
        }

    }

}
