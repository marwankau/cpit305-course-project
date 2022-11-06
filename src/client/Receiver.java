package client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class Receiver extends Thread {

    Socket socket;

    public Receiver(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream;

        try {
            inputStream = socket.getInputStream();
            Scanner recTol = new Scanner(inputStream);


            String line;
            while (true){

                line = recTol.nextLine();

                System.out.println("clint msg:  "+line);


                if (line.equalsIgnoreCase("exit")) break;
            }

            socket.shutdownInput();

        } catch (IOException e) {
            //e.printStackTrace();
        }

    }
}
