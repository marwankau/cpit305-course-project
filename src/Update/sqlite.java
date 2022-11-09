package Update;

import java.rmi.server.SocketSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class sqlite {
    public static void main(String[] args) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:sqlite:mydata", "Root", "122334");

        Scanner keyboard = new Scanner(System.in);
        String line = "";

        while (true) {
            System.out.println("Welcome to //Lib// Database Browser: ");
            System.out.println("1. list all Books");
            System.out.println("2. INSERT new Book");
            System.out.println("3. UPDATE an Book");
            System.out.println("4. DELETE an Book");
            System.out.println("5. SEARCH by Bookid");
            System.out.println("6. Exit");
            System.out.print("Choose : ");
            line = keyboard.nextLine();

            if (line.equals("1")) {
                listBooks(conn);
            } else if (line.equals("2")) {
                InsertBooks(conn);
            } else if (line.equals("3")) {
                // TODO: UPDATE
            } else if (line.equals("4")) {
                // TODO: DELETE
            } else if (line.equals("5")) {
                serachBooks(conn);
            } else if (line.equals("6")) {
                break;
            }
        }

    }

    // SHOW ALL BOOKS
    private static void listBooks(Connection conn) throws SQLException {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM books b ;");
        while (rs.next()) {
            int id = rs.getInt("book_id");
            String name = rs.getString("bookName");
            int isa = rs.getInt("isAvailable");
            String sec = rs.getString("bookSec");

            System.out.printf("%10d  %20s  %20s  %20s\n", id, name, sec, isa);

        }

    }

    // ADD New Book with new ID
    private static void InsertBooks(Connection conn) throws SQLException {
        Scanner keyboards = new Scanner(System.in);
        System.out.print("Name of book : ");
        String Name = keyboards.nextLine();

        PreparedStatement ps = conn.prepareStatement("INSERT INTO books (`book_id`,`bookName`) VALUES (?, ?);");
        ps.setInt(1, generateBookID(conn));
        ps.setString(2, Name);

        int n = ps.executeUpdate();
        System.out.println(n);
    }

    // count bookid
    private static int generateBookID(Connection conn) throws SQLException {
        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery("SELECT MAX(book_id) as 'new_book_id' FROM books ;");
        rs.next();

        return rs.getInt("new_book_id") + 1;
    }

    // SEARCH BY NAME
    private static void serachBooks(Connection conn) throws SQLException {
        Scanner keyboardss = new Scanner(System.in);
        System.out.print("name or part of name to search: ");
        String name = keyboardss.nextLine();

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM books WHERE bookName LIKE ?;");

        ps.setString(1, "%" + name + "%");

        if (ps.execute()) {
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("book_id");
                name = rs.getString("bookName");

                System.out.printf("%10d  %15s\n", id, name);

            }
        } else
            System.out.println("\nNothing found!\n");
    }
}
