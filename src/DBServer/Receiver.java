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
    private Socket socket;
    private Connection conn;

    public Receiver(Socket socket, Connection conn) {
        this.socket = socket;
        this.conn = conn;

    }

    @Override
    public void run() {

        InputStream in;
        try {
            in = socket.getInputStream();
            Scanner recScanner = new Scanner(in);

            String line;
            String querytype;
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
                if (line.equalsIgnoreCase("bye")) {
                    break;
                } else if (line.equalsIgnoreCase("1")) {
                    listBooks(out, writer, conn);
                } else {
                    querytype = recScanner.nextLine();
                    searchForBook(querytype, line, out, writer, conn);
                }
            }

            socket.shutdownInput();
            recScanner.close();

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    private static void listBooks(OutputStream out, PrintWriter writer, Connection conn) {

        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM books b ;");
            while (rs.next()) {
                int id = rs.getInt("book_id");
                String name = rs.getString("bookName");
                int isa = rs.getInt("isAvailable");
                String sec = rs.getString("bookSec");

                writer.printf("%10d %20s %20s %20s\n", id, name, sec, isa);

            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    private static void searchForBook(String queryType, String line, OutputStream out, PrintWriter writer,
            Connection conn)
            throws IOException {
        PreparedStatement ps = null;

        try {
            if (line.equalsIgnoreCase("2")) {

                ps = conn.prepareStatement("SELECT * FROM books WHERE book_id LIKE ?;");
            } else if (line.equalsIgnoreCase("3")) {

                ps = conn.prepareStatement("SELECT * FROM books WHERE bookName LIKE ?;");
            } else if (line.equalsIgnoreCase("4")) {

                ps = conn.prepareStatement("SELECT * FROM books WHERE bookSec LIKE ?;");
            }

            ps.setString(1, "%" + queryType + "%");
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
            e.printStackTrace();
        }

    }

}
