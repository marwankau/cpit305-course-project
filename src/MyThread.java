
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import org.mariadb.jdbc.Connection;

public class MyThread extends Thread {
    Socket client;
    Connection con;

    public MyThread(Socket client, Connection con) {
        this.client = client;
        this.con = con;
    }

    @Override
    public void run() {
        try {
            String line;
            int choice;
            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();
            Scanner receiver = new Scanner(in);
            PrintWriter writer = new PrintWriter(out, true);
            // -----------------------------------------------------------------------------
            line = receiver.nextLine(); // get the msg from the client
            System.out.println(line); // ... is connected //give an information about who is connected to the server
            while (true) {
                writer.println("=====================================");
                writer.println("||Welcome to hotel database browser||");
                writer.println("=====================================");
                writer.println("1. List all rooms");
                writer.println("2. Update room");
                writer.println("3. Search by Room Number");
                writer.println("4. EXIT");
                writer.println("=====================================");
                writer.println("choose: ");
                choice = receiver.nextInt();

                if (choice == 1) {
                    Statement stmt = con.createStatement();
                    ResultSet result = stmt.executeQuery("SELECT * FROM rooms;");
                    writer.println(
                            "========================================================================================");
                    writer.println(
                            "Room Number    Room Type     Visitor Name      CheckIn Date     CheckOut Date    State");
                    while (result.next()) {
                        int room_no = result.getInt("Room_No");

                        String room_type = result.getString("Room_Type");

                        String visitor_name = result.getString("Visitor_Name");

                        String in_date = result.getString("In_Date");

                        String out_date = result.getString("Out_Date");

                        int state = result.getInt("State");

                        writer.println(room_no + "              " + room_type + "        " + visitor_name
                                + "              " + in_date + "             " + out_date + "             " + state);

                    }
                    writer.println(
                            "========================================================================================");
                } 
                
                
                
                
                
                
                else if (choice == 2) {
                    int room_number;
                    String visitor_name;
                    String check_in;
                    String check_out;
                    Statement stmt = con.createStatement();
                    PreparedStatement pstmt = con.prepareStatement("UPDATE rooms SET Visitor_Name=?, In_Date =? , Out_Date = ? , State = 0 WHERE Room_No = ?"); 
                    Scanner updatSc = new Scanner(System.in);
                    writer.println("Which room do you want to update?");
                    room_number = receiver.nextInt();

                    writer.println("Update visitor name");
                    visitor_name = receiver.nextLine();
                    writer.println("Enter check in date DD MM: ");

                    check_in = receiver.nextLine();
                    writer.println("Enter check out date DD MM: ");

                    check_out = receiver.nextLine();

                    pstmt.setString(1, visitor_name); 
                    pstmt.setString(2, check_in); 
                    pstmt.setString(3, check_out); 
                    pstmt.setInt(4, room_number); 
                    pstmt.executeUpdate();
                }

                /*
                 * else if (choice == 3) {
                 * writer.println("BBBBBBBBBBBB");
                 * } else if (choice == 4) {
                 * writer.println("Thank you for using our system ");
                 * break;
                 * } else {
                 * writer.println("Wrong input");
                 * }
                 */
            }

        } catch (Exception e) {
        }
    }

}
