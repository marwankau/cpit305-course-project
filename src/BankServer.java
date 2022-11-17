
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BankServer {

    private static ArrayList<ClientHandler> clients;
    private static ExecutorService pool = Executors.newFixedThreadPool(10);
    public static int k = 0;

    public static void main(String[] args) throws IOException {

        ServerSocket ser = new ServerSocket(50999);
        clients = new ArrayList<>();

        while (true) {
            k++;

            System.out.printf("Waiting for clients on port %d...\n", ser.getLocalPort());
            Socket c = ser.accept();
            System.out.println("\nClient " + k + " Connected...... ");

            ClientHandler ClThread = new ClientHandler(c, k);
            clients.add(ClThread);
            pool.execute(ClThread);

        }

    }

}
