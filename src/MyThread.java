import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.sound.midi.Receiver;

public class MyThread extends Thread {
    Socket client;

    public MyThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            String line;
            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();
            Scanner receiver = new Scanner(in);
            PrintWriter writer = new PrintWriter(out, true);
            // -----------------------------------------------------------------------------
            line = receiver.nextLine(); // get the msg from the client
            System.out.println(line); // ... is connected //give an information about who is connected to the server
            
        } catch (IOException e) {
        }
    }

}
