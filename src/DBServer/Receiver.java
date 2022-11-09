package DBServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Receiver extends Thread {
    static Socket socket;
    static Connection conn;

    public Receiver(Socket socket, Connection conn) {
        this.socket = socket;
        this.conn = conn;

    }

    @Override
    public void run() {

        ReceiveData();

    }

    // private static void listBooks() {

    // try {
    // OutputStream out = socket.getOutputStream();
    // PrintWriter writer = new PrintWriter(out, true);
    // Statement st = conn.createStatement();
    // ResultSet rs = st.executeQuery("SELECT * FROM books b ;");
    // while (rs.next()) {
    // int id = rs.getInt("book_id");
    // String name = rs.getString("bookName");
    // int isa = rs.getInt("isAvailable");
    // String sec = rs.getString("bookSec");

    // writer.printf("%10d %20s %20s %20s\n", id, name, sec, isa);

    // }
    // } catch (IOException e) {
    // } catch (SQLException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // }

    // private void sendresult(String msg) {

    // if (msg.equalsIgnoreCase("1")) {

    // listBooks();
    // }

    // }

    private void ReceiveData() {
        InputStream in;
        try {
            in = socket.getInputStream();
            Scanner recScanner = new Scanner(in);

            String line;
            OutputStream out = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out, true);

            while (true) {

                // if (line.equalsIgnoreCase("1")) {
                // System.out.println(line);
                // listBooks();
                // } else if (line.equalsIgnoreCase("2")) {

                // }

                // else if (line.equalsIgnoreCase("bye"))
                // break;
                line = recScanner.nextLine();

                executeCommand(line);

                if (line.equalsIgnoreCase("bye"))
                    break;

            }

            socket.shutdownInput();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void executeCommand(String line) {

        try {
            OutputStream out = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out, true);

            PreparedStatement ps = conn.prepareStatement(line);

            if (ps.execute()) {
                ResultSet rs = ps.getResultSet();
                while (rs.next()) {
                    int id = rs.getInt("book_id");
                    String name = rs.getString("bookName");
                    int isa = rs.getInt("isAvailable");
                    String sec = rs.getString("bookSec");

                    writer.printf("%10d %20s %20s %20s\n", id, name, sec, isa);

                }
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
        }
    }

}
