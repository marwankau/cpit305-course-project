package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
  public static void main(String[] args) throws IOException {
    Socket socket = new Socket("localhost", 1999);

    InputStream in = socket.getInputStream();
    OutputStream out = socket.getOutputStream();

    DataInputStream dis = new DataInputStream(in);
    DataOutputStream dos = new DataOutputStream(out);
    String line;
    Scanner keyboard = new Scanner(System.in);
    while (true) {
      // Recieve
      while (true) {

        line = dis.readUTF();
        System.out.println(line);
        if ((dis.available()) < 1)
          break;
      }

      // Send
      line = keyboard.nextLine();
      if (line.equalsIgnoreCase("0"))
        break;
      dos.writeUTF(line);

    }

    socket.close();
  }
}