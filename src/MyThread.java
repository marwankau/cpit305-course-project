
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
            String ScannerPassword;
            String ScannerUsername;
            String ReceptionistName;
            String choice;
            int room_number;
            String room_type;
            String visitor_name;
            String check_in;
            String check_out;
            int state;
            // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();
            Scanner receiver = new Scanner(in);
            PrintWriter writer = new PrintWriter(out, true);
            // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            // arrays that contains login information to enter server
            String ReceptionUserName[] = { "RakanSalama", "MoathAlSolami", "AbdulrahmanNahfawi" };                    //To enter the client, you only can by entering username/password
            String ReceptionPassword[] = { "RakanS", "MoathS", "AbdulrahmanN" };                                 //First two arrays are for log in, and 3rd array is to inform who is loggeed in                                 
            String ReceptionName[] = { "Rakan", "Moath", "Abdulrahman" };                                
            // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            Boolean check = true; // For the while loop, If it change the loop will stop ( it will only change if the user did login)
            while (check) { // this loop is made because if the client didn't enter a correct username or password, it will ask him again and again.

                writer.println("Enter username:");
                ScannerUsername = receiver.next();                  // Here we take Username And password from client
                writer.println("Enter Password:");
                ScannerPassword = receiver.next();

                int i = 0; // to reset the i , so it can recheck again the username/password arrays 
                for (i = 0; i < 3; i++) { // it will check all usernames and passwords

                    if (ReceptionUserName[i].equals(ScannerUsername) && ReceptionPassword[i].equals(ScannerPassword)) {
                        check = false; // to get out from while loop
                        ReceptionistName = ReceptionName[i];
                        System.out.println("( " + ReceptionName[i] + " ) " + "is connected");  //give an information about who is connected to the server
                    }
                }
                if (check == false) { // it will skip the wrong username and password msg and get out from the loop.
                    continue;
                }
                writer.println("Username or Password is wrong");
            }
            // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                while (true) {
                    writer.println("===================================");
                    writer.println("|Welcome to hotel database browser|");
                    writer.println("===================================");
                    writer.println("|1. List all rooms                |");
                    writer.println("|2. Update room                   |");                  //Menu and takes the choice from the client
                    writer.println("|3. Search by room number         |");
                    writer.println("|4. Show available rooms          |");
                    writer.println("|5. Set room to default           |");
                    writer.println("|6. EXIT                          |");
                    writer.println("===================================");
                    writer.println("choose: ");
                    choice = receiver.next();
            // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                    if (choice.equals("1")) {
                        
                        Statement stmt = con.createStatement();
                        ResultSet result = stmt.executeQuery("SELECT * FROM rooms;");
                        writer.println("========================================================================================");
                        writer.println("|Room Number   |Room Type    |Visitor Name     |CheckIn Date    |CheckOut Date   |State|");
                        writer.println("|--------------+-------------+-----------------+----------------+----------------+-----|");
                        while (result.next()) {
                            room_number = result.getInt("Room_No");
                            room_type = result.getString("Room_Type");
                            visitor_name = result.getString("Visitor_Name");
                            if(visitor_name == null){
                                visitor_name = "     ";
                            }
                            check_in = result.getString("In_Date");                     //This choice will print the format of all rooms information
                            if(check_in == null){                                                    //and if the room wasn't booked, it won't show visitor name,
                                check_in = "     ";                                                  //check in date, checkout date.
                            }
                            check_out = result.getString("Out_Date");
                            if(check_out == null){
                              check_out= "     ";
                            }
                            state = result.getInt("State");
                            writer.printf("|%-14d|%-13s|%-17s|%-16s|%-16s|%-5d|%n",room_number, room_type, visitor_name, check_in, check_out, state);
                        }
                        writer.println("========================================================================================");
                    }
                // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                    else if (choice.equals("2")) {
                        PreparedStatement pstmt = con.prepareStatement("UPDATE rooms SET Visitor_Name=?, In_Date =? , Out_Date = ? , State = 0 WHERE Room_No = ?"); 

                        writer.println("Which room do you want to update?");
                        room_number = receiver.nextInt();

                        writer.println("Update visitor name");
                        visitor_name = receiver.next();

                        writer.println("Enter check in date DD/MM: ");
                        check_in = receiver.next();

                        writer.println("Enter check out date DD/MM: ");                             //This choice will update Visitor name,check in,check out
                        check_out = receiver.next();                                                  // and the state of the room will be 0 which means it's booked
    
                        pstmt.setString(1, visitor_name); 
                        pstmt.setString(2, check_in); 
                        pstmt.setString(3, check_out); 
                        pstmt.setInt(4, room_number);

                        pstmt.executeUpdate();
                    }
                // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------            
                    else if (choice.equals("3")) {
                        writer.println("ENTER ROOM NO: ");
                        room_number = receiver.nextInt();

                        PreparedStatement ps = con.prepareStatement("SELECT * FROM rooms WHERE Room_No = ?;");
                        ps.setInt(1, room_number);
                        writer.println("========================================================================================");
                        writer.println("|Room Number   |Room Type    |Visitor Name     |CheckIn Date    |CheckOut Date   |State|");
                        writer.println("|--------------+-------------+-----------------+----------------+----------------+-----|");
                        if (ps.execute()) {
                            ResultSet rs = ps.getResultSet();
                            while (rs.next()) { 
                                room_number = rs.getInt("Room_No");
                                room_type = rs.getString("Room_Type");
                                visitor_name = rs.getString("Visitor_Name");         // This choice will print the information for the wanted room
                                check_in = rs.getString("In_Date");
                                check_out = rs.getString("Out_Date");
                                state = rs.getInt("State");

                                writer.printf("|%-14d|%-13s|%-17s|%-16s|%-16s|%-5d|%n",room_number, room_type, visitor_name, check_in, check_out, state);
                            }
                        } else {
                            System.out.println("\nNothing found!\n");
                        }
                        writer.println("========================================================================================");
                    }
                // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                    else if (choice.equals("4")) {
                        Statement stmt = con.createStatement();
                        ResultSet result = stmt.executeQuery("SELECT * FROM rooms WHERE state =1;");
                        writer.println("========================================================================================");
                        writer.println("|Room Number   |Room Type    |Visitor Name     |CheckIn Date    |CheckOut Date   |State|");
                        writer.println("|--------------+-------------+-----------------+----------------+----------------+-----|");
                        while (result.next()) {
                            room_number = result.getInt("Room_No");
                            room_type = result.getString("Room_Type");
                            visitor_name = result.getString("Visitor_Name");     //this choice will show all rooms that are not booked 
                            check_in = result.getString("In_Date");              //in our system if state was 1 it means it's not booked
                            check_out = result.getString("Out_Date");            //if 0 it means it is booked
                            state = result.getInt("State");

                            writer.printf("|%-14d|%-13s|%-17s|%-16s|%-16s|%-5d|%n",room_number, room_type, visitor_name, check_in, check_out, state);
                        }
                        writer.println("========================================================================================");
                    }
                // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                    else if (choice.equals("5")) {

                        PreparedStatement pstmt = con.prepareStatement("UPDATE rooms SET Visitor_Name=null, In_Date =null , Out_Date = null , State = 1 WHERE Room_No = ?");
                        writer.println("Which room do you want to update?");
                        room_number = receiver.nextInt();

                        pstmt.setInt(1, room_number);                               // It will set visitor name, check in, checkout and state to deafult
                        pstmt.executeUpdate();
                    }
                // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------ 
                    else if (choice.equalsIgnoreCase("EXIT") || choice.equals("6")) {
                        writer.println("===================================");
                        writer.println("| Thank you for using our system  |");
                        writer.println("===================================");
                        break;
                    }
                // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------  
                    else {
                        writer.println("Wrong choice :(");
                    }
                }
            }catch (Exception e) {
            }
        }

    }
