import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.ParseException;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class Player {
    private Socket s;
    private PrintWriter WriteToServer;
    private Scanner ReadFromServer;
    
    public String welcomMsg ="\n\tROCK \t\tPAPER \t\tSCISSORS \n   _______                 _______                    _______\n---'   ____)            ---'   ____)____           ---'   ____)____\n      (_____)                     ______)                    ______)\n      (_____)                     _______)                __________)\n      (____)                    _______)                 (____)\n---.__(___)             ---.__________)            ---.__(___)\n";
   
    public static void main(String[] args) throws IOException, ParseException, SQLException {

        Player p = new Player();
        p.menu();

    }

    public void menu() throws IOException, ParseException, SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cpit305-project", "root", "moe123");
        Statement stat = conn.createStatement();

        Scanner in = new Scanner(System.in);
        String choice = "0";
        String name = "";
        String pass = "";
        String IDs = "";
        while (true) {
            if (choice.equals("0")) {
                do{
                System.out.println("======================================================");    
                System.out.println(welcomMsg);
                System.out.println("\n==================== RPS Game ====================");
                System.out.println("Welcome to our game\n");
                System.out.println("1. New player?");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Choose from above: ");
                choice = in.next();
                
                if (choice.equals("1")) {

                        boolean usedName = false;

                    
                    do{
                    System.out.println("Enter your username: ");
                    name = in.next();
                
                    System.out.println("enter your password: ");
                    pass = in.next();
       
                      usedName = check(name,stat);
                      
                      if(usedName){
                       System.out.println("username is used please try another one..");
                        }
                    
                    
                    }while(usedName);
                    
                    System.out.println("Hey " + name + " your registration has been successfully completed");
                     IDs = UUID.randomUUID().toString().substring(2,5);
                    System.out.println("Your ID is " + IDs);
                    stat.executeQuery("insert into player (ID, username, password, Wins) values('"+IDs+"',"+"'"+name+"'"+","+"'"+pass+"'"+","+0+")");

                        
                        
                            
                  
                }

                    else if (choice.equals("2")) {
                        boolean NotRegistered = false;
                        String TryOrBack = "";

                    do{
                    System.out.println("Enter your username: ");
                    name = in.next();
                    System.out.println("enter your password: ");
                    pass = in.next();
                                

                
                   NotRegistered = CheckLogin(name, pass, stat);
                            if(NotRegistered){
                             System.out.println("Your username/password is incorrect ");
                                System.out.println("*type \"back\" to go back or \"press any key\" to continue trying..");
                                TryOrBack = in.next();

                            }

                            if (TryOrBack.equalsIgnoreCase("back")) {
                                choice = "0";
                                break;
                            } else {
                                continue;
                            }

                        } while (NotRegistered);

                        ResultSet r = stat.executeQuery(
                                "select ID from player where username = '" + name + "' and password = '" + pass + "'");

                        while (r.next()) {
                            IDs = r.getString("ID");
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
                    System.out.println("2. Recored you won");
                    System.out.println("3. Tutorial");
                    System.out.println("4. Exit");
                    System.out.print("Choose from above: ");
                    choice = in.next();

                    if (choice.equals("1")) {
                        ConnectToServer();

                        Scanner tool = new Scanner(System.in);
                        String choose;
                        String res;

                        int rounds = 1;
                        while (rounds <= 3) {
                            System.out.println(ReadFromServer.nextLine());
                            System.out.println(ReadFromServer.nextLine());
                            choose = tool.nextLine();
                            WriteToServer.println(choose);
                            res = ReadFromServer.nextLine();
                            System.out.println(res);

                            res = ReadFromServer.nextLine();
                            System.out.println(res);

                            res = ReadFromServer.nextLine();
                            System.out.println(res);

                            res = ReadFromServer.nextLine();
                            System.out.println(res);
                            rounds++;

                        }

                        res = ReadFromServer.nextLine();
                        System.out.println(res);

                    }

                    else if (choice.equals("2")) {

                    do {
                        System.out.println("not implemented yet.." + " Press " + "1"+" to exit record page");
                        choice = in.next();
                    } while (!choice.equals("1") );

                    }

                    else if (choice.equals("3")) {

                        do {
                            System.out.println("\n===== Rock Paper Scissors =====\n"
                                    + "* In RPS game you can play online with any player.\n" +
                                    "* You will play for THREE rounds.\n" +
                                    "* The one gets the highest points will win the game.\n\n" +

                                "* Press" + "1" + " to choose Rock\n" +
                                "* Press " + "2" + " to choose Paper\n" +
                                "* Press " + "3" + " tp choose Scissor\n" +
                                "Press "+"1"+" to exit tutorial page");
                        choice = in.next();
                    } while (!choice.equals("1"));

                    }


                else if(choice.equals("4") ){
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

    private boolean check(String name, Statement stat) throws SQLException {

        ResultSet r = stat.executeQuery("select * from player where username = '" + name + "'");
        if (r.next()) {
            return true;
        } else {
            return false;
        }

    }

    private void ConnectToServer() throws IOException {
        s = new Socket("localhost", 5000);

        WriteToServer = new PrintWriter(s.getOutputStream(), true);
        ReadFromServer = new Scanner(s.getInputStream());

        String msg = ReadFromServer.nextLine();
        String msg2 = ReadFromServer.nextLine();

        // Alert player
        System.out.println(msg);
        System.out.println(msg2);

    }

    private boolean CheckLogin(String name, String pass, Statement stat) throws SQLException {
        ResultSet r = stat
                .executeQuery("select * from player where username = '" + name + "' and password ='" + pass + "'");

        if (r.next()) {
            return false;
        }

        else {
            return true;
        }
    }

}