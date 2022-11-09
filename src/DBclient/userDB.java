package DBclient;


import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class userDB {
    public static void main(String[] args) {
        Socket socket;
        try {
            socket = new Socket(InetAddress.getLocalHost(), 2000);

            Reader receiver = new Reader(socket);
            receiver.start();
            new Sender(socket).start();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

       

       
    }
}
