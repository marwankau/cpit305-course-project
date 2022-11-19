package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {
  public static void main(String[] args) {

    // try with resources
    try (Socket socket = new Socket("localhost", 1999)) {

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
      keyboard.close();
      dis.close();
      dos.close();

      // When hostname is incorrect
    } catch (UnknownHostException e) {
      System.out.println("Host name is not found");
      // When client disconnect
    } catch (NoSuchElementException e) {
      System.out.println("Disconnected");
      // When server is offline
    } catch (ConnectException e) {
      System.out.println("Server is offline");
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}