package DBclient;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Sender extends Thread {
  Socket socket;

  public Sender(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    // String line;

    try {
      OutputStream out = socket.getOutputStream();
      PrintWriter writer = new PrintWriter(out, true);

      // while (true) {
      // Scanner keyboard = new Scanner(System.in);
      // System.out.print("msg: ");
      // line = keyboard.nextLine();

      // writer.println(line);
      // if (line.equalsIgnoreCase("bye"))
      // break;

      // }

      while (true) {
        writer.println(libraryInterface());
        if (libraryInterface().equalsIgnoreCase("bye"))
          break;

      }

      socket.shutdownOutput();

    } catch (IOException e) {
    }
  }

  public String libraryInterface() {
    String line;
    StringBuilder sb = null;
    String command;
    String bookRef;
    Scanner keyboard = new Scanner(System.in);
    while (true) {

      System.out.println("\tWelcome to library management system\t");
      System.out.println("\t1.Search for a book by ID\t");
      System.out.println("\t2.Search for a book by Name\t");
      System.out.println("\t3.Search for books by Section\t");
      System.out.print("Choice : ");
      line = keyboard.nextLine();
      if (line.equalsIgnoreCase("1")) {
        command = "SELECT * FROM books WHERE book_id LIKE ";
        System.out.print("Enter ID to search for: ");
        bookRef = keyboard.nextLine();
        sb = new StringBuilder(command);
        sb.append(bookRef);
        sb.append(";");
        return sb.toString();
      }
      if (line.equalsIgnoreCase("2")) {
        command = "SELECT * FROM books WHERE book_name LIKE ";
        System.out.print("Enter Name to search for: ");
        bookRef = keyboard.nextLine();
        sb = new StringBuilder(command);
        sb.append(bookRef);
        sb.append(";");
        return sb.toString();
      }
      if (line.equalsIgnoreCase("3")) {
        command = "SELECT * FROM books WHERE bookSec LIKE ";
        System.out.print("Enter Section to search for: ");
        bookRef = keyboard.nextLine();
        sb = new StringBuilder(command);
        sb.append(bookRef);
        sb.append(";");
        return sb.toString();
      } else if (line.equalsIgnoreCase("bye")) {
        sb = new StringBuilder("bye");
        break;
      }

    }
    return sb.toString();

  }
}
