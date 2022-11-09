
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
            String ScannerPassword;
            String ScannerUsername;
            String ReceptionistName;
            String line;
            String choice;
            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();
            Scanner receiver = new Scanner(in);
            Scanner H = new Scanner(in);
            PrintWriter writer = new PrintWriter(out, true);
            // -----------------------------------------------------------------------------
            // arrays that contains login information to enter server
            String ReceptionUserName[] = { "A", "MoathAlSolami", "AbdulrahmanNahfawi" };
            String ReceptionPassword[] = { "A", "MoathS", "AbdulrahmanN" };
            String ReceptionName[] = { "Rakan", "Moath", "Abdulrahman" };

            Boolean check = true; // For the while loop, If it change the loop will stop ( it will only change if
                                  // the user did login)
            while (check) { // this loop is made because if the receptionist didn't enter a correct username
                            // or password, it will ask him again and again.

                // Here we take Username And password
                writer.println("Enter username:");
                ScannerUsername = receiver.next();
                writer.println("Enter Password:");
                ScannerPassword = receiver.next();

                int i = 0; // to reset the i , so it can recheck again V
                for (i = 0; i < 3; i++) { // it will check all usernames and passwords

                    if (ReceptionUserName[i].equals(ScannerUsername) && ReceptionPassword[i].equals(ScannerPassword)) {
                        check = false; // to get out from while loop
                        ReceptionistName = ReceptionName[i];
                        System.out.println("( " + ReceptionName[i] + " ) " + "is connected"); // ... is connected //give an information about who is connected to the server
                    }
                }
                if (check == false) { // it will skip the wrong username and password and get out from the loop.
                    continue;
                }
                writer.println("Username or Password is wrong");
            }

                while (true) {
                    writer.println("=====================================");
                    writer.println("||Welcome to hotel database browser||");
                    writer.println("=====================================");
                    writer.println("1. List all rooms");
                    writer.println("2. Update room");
                    writer.println("3. Search by Room Number");// SELCET * FROM ROOMS WHERE ROOM_NO =?
                    writer.println("4. AVALIABLE ROOMS");// SELCET * FROM ROOMS WHERE STATE=1 LOOP
                    writer.println("5. DEAFULT");// "UPDATE rooms SET Visitor_Name=?, In_Date =? , Out_Date = ? ,
                                                 // State
                                                 // = 0 WHERE Room_No = ?"
                    writer.println("6. EXIT");
                    writer.println("=====================================");
                    writer.println("choose: ");
                    choice = receiver.next();

                    if (choice.equals("1")) {
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
                            writer.printf("%d %19s %12s %14s %16s %15d%n",   room_no, room_type, visitor_name, in_date, out_date, state);

                        }
                        writer.println(
                                "========================================================================================");
                    }

                    else if (choice.equals("2")) {

                        int room_number;
                        String visitor_name;
                        String check_in;
                        String check_out;
    
                        PreparedStatement pstmt = con.prepareStatement("UPDATE rooms SET Visitor_Name=?, In_Date =? , Out_Date = ? , State = 0 WHERE Room_No = ?"); 
                        writer.println("Which room do you want to update?");
                        room_number = receiver.nextInt();
    
                        writer.println("Update visitor name");
                        visitor_name = receiver.next();
                        writer.println("Enter check in date DD/MM: ");
    
                        check_in = receiver.next();
                        writer.println("Enter check out date DD/MM: ");
    
                        check_out = receiver.next();
    
                        pstmt.setString(1, visitor_name); 
                        pstmt.setString(2, check_in); 
                        pstmt.setString(3, check_out); 
                        pstmt.setInt(4, room_number);
                        pstmt.executeUpdate();
    
                    }

                    else if (choice.equals("3")) {
                        String visitor_name;
                        String check_in;
                        String check_out;
                        int room_number;
                        writer.println("ENTER ROOM NO: ");
                        room_number = receiver.nextInt();
                        PreparedStatement ps = con.prepareStatement("SELECT * FROM rooms WHERE Room_No = ?;");

                        ps.setInt(1, room_number);

                        if (ps.execute()) {
                            ResultSet rs = ps.getResultSet();
                            while (rs.next()) {
                                room_number = rs.getInt("Room_No");
                                visitor_name = rs.getString("Visitor_Name");
                                check_in = rs.getString("In_Date");
                                check_out = rs.getString("Out_Date");
                                int state = rs.getInt("State");

                                writer.println("room_number= " + room_number);
                                writer.println("visitor_name= " + visitor_name);
                                writer.println("In_Date= " + check_in);
                                writer.println("Out_Date= " + check_out);
                                writer.println("state= " + state);
                            }
                        } else {
                            System.out.println("\nNothing found!\n");
                        }

                    }

                    else if (choice.equals("4")) {
                        Statement stmt = con.createStatement();
                        ResultSet result = stmt.executeQuery("SELECT * FROM rooms WHERE state =1;");
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
                                    + "              " + in_date + "             " + out_date + "             "
                                    + state);

                        }
                        writer.println(
                                "========================================================================================");
                    }

                    else if (choice.equals("5")) {

                        int room_number;
                        String visitor_name;
                        String check_in;
                        String check_out;

                        PreparedStatement pstmt = con.prepareStatement(
                                "UPDATE rooms SET Visitor_Name=?, In_Date =? , Out_Date = ? , State = 1 WHERE Room_No = ?");
                        writer.println("Which room do you want to update?");
                        room_number = receiver.nextInt();

                        visitor_name = "null";

                        check_in = "null";

                        check_out = "null";

                        pstmt.setString(1, visitor_name);
                        pstmt.setString(2, check_in);
                        pstmt.setString(3, check_out);
                        pstmt.setInt(4, room_number);
                        pstmt.executeUpdate();
                    } else if (choice.equalsIgnoreCase("EXIT") || choice.equals("6")) {
                        writer.println("THANK YOU : ");
                        break;
                    } else {
                        writer.println("try again ");
                    }
                }

            }catch (Exception e) {
            }
        }

    }
