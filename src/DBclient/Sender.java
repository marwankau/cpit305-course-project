package DBclient;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

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
        libraryInterface(writer);
      }

    } catch (IOException e) {
    }
  }

  private void libraryInterface(PrintWriter writer) throws IOException {
    String line;

    String bookRef;
    Scanner keyboard = new Scanner(System.in);
    while (true) {

      System.out.println("\tWelcome to library management system\t");
      System.out.println("\t1. List all books\t");
      System.out.println("\t2.Search for a book by ID\t");
      System.out.println("\t3.Search for a book by Name\t");
      System.out.println("\t4.Search for books by Section\t");
      System.out.println("\t5.update book availability status\t");
      System.out.println("\t6.exit\t");
      System.out.println();
      System.out.print("Choice : ");
      line = keyboard.nextLine();
      if (line.equalsIgnoreCase("1")) {
        writer.println(line);
      } else if (line.equalsIgnoreCase("2")) {
        writer.println(line);
        System.out.print("Enter ID to search for: ");
        bookRef = keyboard.nextLine();
        writer.println(bookRef);
      }
      if (line.equalsIgnoreCase("3")) {
        writer.println(line);
        System.out.print("Enter name to search for: ");
        bookRef = keyboard.nextLine();
        writer.println(bookRef);
      }
      if (line.equalsIgnoreCase("4")) {
        writer.println(line);
        System.out.print("Enter section to search for: ");
        bookRef = keyboard.nextLine();
        writer.println(bookRef);

      }
      if (line.equalsIgnoreCase("5")) {
        writer.println(line);
        System.out.print("Enter book ID to change its status: ");
        bookRef = keyboard.nextLine();
        writer.println(bookRef);

      } else if (line.equalsIgnoreCase("6")) {
        writer.println("bye");
        break;

      }

    }
    socket.shutdownOutput();
    keyboard.close();
  }
}
