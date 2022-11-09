package DBclient;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class Reader extends Thread {
  Socket socket;

  public Reader(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    InputStream in;
    try {
      in = socket.getInputStream();
      Scanner recScanner = new Scanner(in);

      String line;

      while (true) {
        line = recScanner.nextLine();
        if (line.equalsIgnoreCase("bye"))
          break;

        System.out.println(line);

      }

      socket.shutdownInput();
      recScanner.close();
    } catch (IOException e) {
    }
  }
}
