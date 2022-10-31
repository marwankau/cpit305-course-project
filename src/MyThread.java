import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MyThread extends Thread {
    Socket client;

    public MyThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            String line;
            int choice;
            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();
            Scanner receiver = new Scanner(in);
            PrintWriter writer = new PrintWriter(out, true);
            // -----------------------------------------------------------------------------
            while (true) {
                line = receiver.nextLine(); // get the msg from the client
                System.out.println(line); // ... is connected //give an information about who is connected to the server
                try {
                    choice = receiver.nextInt();
                    System.out.println("The choice was: " + choice);
                } catch (InputMismatchException e) {
                    System.out.println("Wrong input");
                }
                writer.close();
            }
        } catch (IOException e) {
        }
    }

}
