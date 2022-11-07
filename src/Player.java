import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.ParseException;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Player {
    private Socket s;
    private PrintWriter WriteToServer;
    private Scanner ReadFromServer;
    private DataOutputStream dos;
  

    public static void main(String[] args) throws IOException, ParseException, SQLException {

        Player p = new Player();
        p.menu();
    }

    public void menu() throws IOException, ParseException, SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cpit305-project", "root",
                "moe123");
        Statement stat = conn.createStatement();
        PreparedStatement ps = conn.prepareStatement("");

        String Tutorial ="\nROCK \t\t\tPAPER \t\t\tSCISSORS \n   _______                 _______                    _______\n---'   ____)            ---'   ____)____           ---'   ____)____\n      (_____)                     ______)                    ______)\n      (_____)                     _______)                __________)\n      (____)                    _______)                 (____)\n---.__(___)             ---.__________)            ---.__(___)\n";

        Scanner in = new Scanner(System.in);
        String choice = "0";
        String name = "";
        String pass = "";
        int IDs = 0;

        while (true) {
            if (choice.equals("0")) {
                do {
                    System.out.println("\n==================== RPS Game ====================");
                    System.out.println("Welcome to our game\n");
                    System.out.println("1. New player?");
                    System.out.println("2. Login");
                    System.out.println("3. Exit");
                    System.out.print("Choose from above: ");
                    choice = in.next();

                    if (choice.equals("1")) {

                        boolean usedName = false;

                        do {
                            System.out.println("\n#### SIGN-UP ####");
                             System.out.println("*Your username should be less than 10 charaters*");
                             System.out.println("*Your password should be less than 10 digits*");
                            System.out.print("\nEnter your username: ");
                            name = in.next();
                            System.out.print("Enter your password: ");
                            pass = in.next();
                             System.out.print("\n");
 

                            usedName = checkReg(name, ps, conn);

                            if (usedName) {
                                System.out.println("Username is used, please try another one!");
                            }
                            
                            else if (name.length() > 25){
                                System.out.println("Your username is more than than 10 charaters!");
                            }
                            
                           else if (pass.length() > 10){
                           System.out.println("Your password is more than 10 digits!");

                           }

                            

                        } while (usedName || name.length() > 25 || pass.length() > 10);

                        System.out.println("Hey " + name + " your registration has been successfully completed");
                        IDs = generateID(conn);
                        System.out.println("Your ID is " + IDs);
                        ps = conn.prepareStatement("insert into player (ID, username, password, Wins) values(?,?,?,?)");

                        ps.setInt(1, IDs);
                        ps.setString(2, name);
                        ps.setString(3, pass);
                        ps.setInt(4, 0);

                        ps.executeUpdate();

                    }

                    else if (choice.equals("2")) {
                        boolean NotRegistered = false;
                        String TryOrBack = "";

                        do {
                            System.out.println("\n#### LOGIN ####");
                            System.out.print("Enter your username: ");
                            name = in.next();
                            System.out.print("Enter your password: ");
                            pass = in.next();

                            NotRegistered = CheckLogin(name, pass, ps, conn);
                            if (NotRegistered) {
                            System.out.println("Your username/password is incorrect!");

                                do{
                             System.out.println("\n#### HELP ####");
                            System.out.println("b. Back to main menu");
                            System.out.println("t. Try again");      
                            System.out.print("Choose from above: ");

                                TryOrBack = in.next();
                                System.out.print("\n");
                                }while(!TryOrBack.equalsIgnoreCase("b") && !TryOrBack.equalsIgnoreCase("t"));

                            }

                            if (TryOrBack.equalsIgnoreCase("b")) {
                                choice = "0";
                                break;
                            } 

                        } while (NotRegistered);

                        if (!choice.equals("0")) {
                            ps = conn.prepareStatement("select ID from player where username = ? and password = ?");

                            ps.setString(1, name);
                            ps.setString(2, pass);
                            ps.execute();
                            ResultSet r = ps.getResultSet();

                            if (r.next()) {
                                IDs = r.getInt("ID");
                            }
                        }

                    }

                    else if (choice.equals("3")) {
                        System.out.println("Thank you for trying our game ^_^");
                        break;

                    }
                } while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3"));

            }

            else if (choice.equals("1") || choice.equals("2")) {
                do {
                    System.out.println("\n==================== RPS Game ====================\n");
                    System.out.println("Welcome " + name + " ID:" + IDs + "\n");
                    System.out.println("1. Start game");
                    System.out.println("2. Your Best Record");
                    System.out.println("3. Tutorial");
                    System.out.println("4. Exit");
                    System.out.print("Choose from above: ");
                    choice = in.next();

                    if (choice.equals("1")) {
                        ConnectToServer();

                        Scanner tool = new Scanner(System.in);
                        String choose;
                        String res;

                        dos.writeInt(IDs);
                        WriteToServer.println(name);
              
                        int rounds = 1;
                         try{
                        while (rounds <= 3) {
                             
                            System.out.println("\n"+ReadFromServer.nextLine());
                            System.out.println(ReadFromServer.nextLine());
                           
                            
                            do {
                                 System.out.print("->");
                                choose = tool.nextLine();

                                if (!choose.equals("1") && !choose.equals("2") && !choose.equals("3")) {
                                    System.out.println("Try [1:Rock 2:Paper 3:Scissor]:");
                                }
                            } while (!choose.equals("1") && !choose.equals("2") && !choose.equals("3"));
                            
                          
                            WriteToServer.println(choose);

               
                            res = ReadFromServer.nextLine();
                            System.out.println("\n"+res);

                            res = ReadFromServer.nextLine();
                            System.out.println(res);

                            res = ReadFromServer.nextLine();
                            System.out.println(res);
                            rounds++;
                      
                        }
                            
                         res = ReadFromServer.nextLine();
                        System.out.println("\n"+res);

                         }
                            catch(Exception e){
                            
                                System.out.println("Enemy has disconnected");
                                break;
                            }
                         
                         s.close();
                        WriteToServer.close();
                        ReadFromServer.close();
                    

                    }

                    else if (choice.equals("2")) {
                        do {

                     

                            ResultSet r = stat.executeQuery("select * from gameplay where winner = '" + name + "'");
                            boolean rs;
                            rs = r.next();

                            if (rs) {
                                
                System.out.print("\n+--------+---------+--------+------------+\n" +
                                  "| GameID | Player2 | result  | Gdate      |\n" +
                                  "+--------+---------+--------+------------+\n");
                                while (rs) {

                                    System.out.printf("%4s %9s  %11s %12s", r.getString("GameID"),
                                            r.getString("Player2"), r.getString("Result"), r.getString("Gdate"));
                                    System.out.println("\n" + "+--------+---------+--------+------------+");
                                    rs = r.next();

                                }
                            } else {

                                System.out.println("no records found..play harder!");
                            }
                            
                            
                            r = stat.executeQuery("select Wins from player where username = '" + name + "'");
                            if (r.next()) {
                                int winsNum = r.getInt("Wins");

                                if (winsNum % 2 == 0) {
                                    System.out.println("You have won " + winsNum + " games");
                                } else {
                                    System.out.println("You have won " + winsNum + " game");
                                }
                            }

                            System.out.print("Press " + "E" + " to exit record page: ");
                            choice = in.next();
                        } while (!choice.equalsIgnoreCase("E"));

                    }

                    else if (choice.equals("3")) {

                        do {
                            System.out.print("\n===== Rock Paper Scissors =====\n"
                                    + "* In RPS game you can play online with any player.\n" +
                                    "* You will play for THREE rounds.\n" +
                                    "* The one gets the highest points will win the game.\n" +
                                        Tutorial
                                    +
                                    "\n* Press " + "1" + " to choose Rock\n" +
                                    "* Press " + "2" + " to choose Paper\n" +
                                    "* Press " + "3" + " tp choose Scissor\n" +

                                    "\nPress " + "E" + " to exit tutorial page: ");
                            choice = in.next();
                        } while (!choice.equalsIgnoreCase("E"));

                    }

                    else if (choice.equals("4")) {
                        System.out.println("Thank you for trying our game ^_^");
                        break;
                    }

                } while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4"));

            }

            else if (choice.equals("4") || choice.equals("3")) {
                break;
            }
        }

    }

    private boolean checkReg(String name, PreparedStatement ps, Connection conn) throws SQLException {

        ps = conn.prepareStatement("select * from player where username = ?");
        ps.setString(1, name);
        ps.execute();
        ResultSet r = ps.getResultSet();
        if (r.next()) {
            return true;
        } else {
            return false;
        }

    }

    private void ConnectToServer() throws IOException {
        s = new Socket("localhost", 5000);

        InputStream in = s.getInputStream();
        OutputStream out = s.getOutputStream();
      
         dos = new DataOutputStream(out);
        
        WriteToServer = new PrintWriter(out, true);
        ReadFromServer = new Scanner(in);
        
        

        String Notify = ReadFromServer.nextLine();
        String Notify2 = ReadFromServer.nextLine();

        // Alert player
        System.out.println(Notify);
        System.out.println(Notify2);
       

    }

    private boolean CheckLogin(String name, String pass, PreparedStatement ps, Connection conn) throws SQLException {
        ps = conn.prepareStatement("select ID from player where username = ? and password = ?");

        ps.setString(1, name);
        ps.setString(2, pass);
        ps.execute();
        ResultSet r = ps.getResultSet();
        if (r.next()) {
            return false;
        }

        else {
            return true;
        }
    }
 
    private static int generateID(Connection conn) throws SQLException {
        Statement stat = conn.createStatement();

        ResultSet rs = stat.executeQuery("select MAX(ID) as 'NEW_ID' FROM player;");

        if (rs.next() == false) {
            return 1;
        }

        else {
            return rs.getInt("new_id") + 1;

        }
    }
}