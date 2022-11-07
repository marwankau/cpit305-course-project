import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(2000);

        System.out.println("Server is Online");
            while (true) {
                Socket client = server.accept();
                
                MyThread thread = new MyThread(client);
                thread.start();
        }
    }
}