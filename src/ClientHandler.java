
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ClientHandler implements Runnable {

    final private Socket c;
    final private BufferedReader bfr;
    final private BufferedWriter bfw;
    final private int k;

    public ClientHandler(Socket c, int k) throws IOException {
        this.c = c;
        bfr = new BufferedReader(new InputStreamReader(c.getInputStream()));
        bfw = new BufferedWriter(new OutputStreamWriter(c.getOutputStream()));
        this.k = k;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);

        String msg;
        try {

            while (true) {

                msg = bfr.readLine();
                System.out.println("Enter: ");

                System.out.println(" Client " + k + ": " + msg);

                if (msg.equalsIgnoreCase("exit")) {
                    break;
                } else if (msg.equalsIgnoreCase("bye")) {

                    bfw.write("** YOU OUT OF SERVICE **");
                    bfw.newLine();
                    bfw.flush();
                    System.out.println("** Client " + k
                            + " Logout From Contacting Srever for a moment and may join again..... **");

                } else if (msg.equalsIgnoreCase("name")) {

                    bfw.write("YOU ARE ");
                    bfw.newLine();
                    bfw.flush();
                } else if (msg.equalsIgnoreCase("hi") || msg.equalsIgnoreCase("hello") || msg.equalsIgnoreCase("hai")
                        || msg.equalsIgnoreCase("hey") || msg.equalsIgnoreCase("hay")) {

                    bfw.write("-- WELCOME ^ LEGEND --");
                    bfw.newLine();
                    bfw.flush();
                } else if (msg.equalsIgnoreCase("date")) {
                    Date d = new Date();
                    SimpleDateFormat sd = new SimpleDateFormat("\t\t hh:mm a -------");
                    bfw.write(sd.format(d));

                    SimpleDateFormat sd2 = new SimpleDateFormat(" dd/mm/yyyy\n");
                    bfw.write(sd2.format(d));
                    bfw.newLine();
                    bfw.flush();
                } else if (msg.equalsIgnoreCase("?")) {
                    bfw.write("");
                    bfw.newLine();
                    bfw.flush();
                } else {
                    bfw.write("Message Received.");
                    bfw.newLine();
                    bfw.flush();
                }

            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}