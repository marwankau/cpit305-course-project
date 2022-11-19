import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.text.ParseException;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Player {
// data fileds
    private Socket s;
    private PrintWriter WriteToServer;
    private Scanner ReadFromServer;
    private DataOutputStream dos;
  
    // main method
    public static void main(String[] args) throws IOException, ParseException, SQLException {

        Player p = new Player();
        p.menu();
    }

    // display menu method
    public void menu() throws IOException, ParseException, SQLException {

        // connect to the database
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cpit305-project", "root",
                "moe123");
        Statement stat = conn.createStatement();
        PreparedStatement ps = conn.prepareStatement("");

        // shapes of the tools used in tutorial page
        String Tutorial ="\nROCK \t\t\tPAPER \t\t\tSCISSORS \n   _______                 _______                    _______\n---'   ____)            ---'   ____)____           ---'   ____)____\n      (_____)                     ______)                    ______)\n      (_____)                     _______)                __________)\n      (____)                    _______)                 (____)\n---.__(___)             ---.__________)            ---.__(___)\n";

        // Scanners to read input for menu options
        Scanner in = new Scanner(System.in);
        Scanner tool = new Scanner(System.in);
        Scanner login = new Scanner(System.in);
        Scanner SignUp = new Scanner(System.in);

// initiates variables to store the inputs from users
        String choice = "0";
        String name = "";
        String pass = "";
        int IDs = 0;

        while (true) {

            // if input is 0 -which is the default value- it will display the main menu
            if (choice.equals("0")) {
                do {
                     // main-menu
                    System.out.println("\n==================== RPS Game ====================");
                    System.out.println("Welcome to our game\n");
                    System.out.println("1. New player?");
                    System.out.println("2. Login");
                    System.out.println("3. Exit");
                    System.out.print("Choose from above: ");
                    choice = in.next();

                   // if user wants to register
                    if (choice.equals("1")) {
                        // declare boolean variables for checking
                        boolean usedName = false;
                        boolean hasSpace = false;

                        do {
                            
                           
                            // sign-up 
                            System.out.println("\n#### SIGN-UP ####");
                             System.out.println("*Your username should be less than 10 charaters*");
                             System.out.println("*Your password should be less than 10 digits*");
                            System.out.print("\nEnter your username: ");
                            name = SignUp.nextLine();
                            System.out.print("Enter your password: ");
                            pass = SignUp.nextLine();
                            System.out.print("\n");
                            
                            hasSpace = false;
                            // if method return false that means no player used the entered username
                            usedName = checkReg(name, ps, conn);

                            if (usedName) {
                                // if true then it will print a warning message
                                System.out.println("Username is used, please try another one!");
                            }
                            
                            // checking the length of name and password to be able to store it in database
                            else if (name.length() > 25){
                                 // if true then it will print a warning message
                                System.out.println("Your username is more than than 10 charaters!");
                            }
                            
                           else if (pass.length() > 10){
                            // if true then it will print a warning message
                           System.out.println("Your password is more than 10 digits!");

                           }

                           // check if name has spaces in it
                           for (int i = 0; i < name.length() ; i++) {

                            if (name.charAt(i) == ' '){
                                // if true then make hasSpace variable true
                                hasSpace = true;                            
                            }


                            if(hasSpace){
                            // if true then it will print a warning message
                            System.out.println("Your usrname/password has spaces!");
                            //  get out from loop 
                             break;
                            }
                            
                        }


                        // check if name has spaces in it
                        for (int i = 0; i < pass.length() ; i++) {

                            if (pass.charAt(i) == ' '){
                        // if true then make hasSpace variable true
                                hasSpace = true;                            
                            }


                            if(hasSpace){
                           // if true then it will print a warning message
                             System.out.println("Your usrname/password has spaces!");
                             //  get out from loop 
                             break;
                            }
                            
                        }

                            
                        // repeat asking the users inputs for name and password as long as one of these conditions are true
                        } while (hasSpace||usedName || name.length() > 25 || pass.length() > 10);


                        // after registration the program will print a successful message
                        System.out.println("Hey " + name + " your registration has been successfully completed");
                        // generate new ID for player 
                        IDs = generateID(conn);
                        System.out.println("Your ID is " + IDs);

                        // insert new player to database 
                        ps = conn.prepareStatement("insert into player (ID, username, password, Wins) values(?,?,?,?)");

                        ps.setInt(1, IDs);
                        ps.setString(2, name);
                        ps.setString(3, pass);
                        ps.setInt(4, 0);

                        ps.executeUpdate();

                    }

                    // if user want to login
                    else if (choice.equals("2")) {
                        // check variable
                        boolean NotRegistered = false;

                        // stores inputs in the help menu
                        String TryOrBack = "";

                        do {
                            // login 
                            System.out.println("\n#### LOGIN ####");
                            System.out.print("\nEnter your username: ");
                            name = login.nextLine();
                            System.out.print("Enter your password: ");
                            pass = login.nextLine();
                            System.out.print("\n");

                            // check if player is already registered or not
                            NotRegistered = CheckLogin(name, pass, ps, conn);

                            // if not registerd
                            if (NotRegistered) {
                                // print warning
                            System.out.println("Your username/password is incorrect!");

                                do{
                                    // provide user with a help menu
                             System.out.println("\n#### HELP ####");
                            System.out.println("b. Back to main menu");
                            System.out.println("t. Try login again");      
                            System.out.print("Choose from above: ");

                            // should enter back to get register or try to enter another username/password 
                                TryOrBack = in.next();
                                System.out.print("\n");

                                // repeat if the input is not b or t
                                }while(!TryOrBack.equalsIgnoreCase("b") && !TryOrBack.equalsIgnoreCase("t"));

                            }

                            
                            // if user entered b in help menu
                            if (TryOrBack.equalsIgnoreCase("b")) {
                         // will give choice variable the default value to get back to the main menu after breaking the loop
                                choice = "0";
                                break;
                            } 

                        } while (NotRegistered);

                        // if player loged in succussfuly then fetch his ID from database and display it in game menu
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
                    // if user wants to exit 
                    else if (choice.equals("3")) {
                        System.out.println("Thank you for trying our game ^_^");
                        // break from main menu loop 
                        break;

                    }
                    // repeat when user do enter somthing other than options in menu
                } while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3"));

            }

            else if (choice.equals("1") || choice.equals("2")) {
                do {
                    // Game menu
                    System.out.println("\n==================== RPS Game ====================\n");
                    System.out.println("Welcome " + name + " ID:" + IDs + "\n");
                    System.out.println("1. Start game");
                    System.out.println("2. Your Best Record");
                    System.out.println("3. Tutorial");
                    System.out.println("4. Exit");
                    System.out.print("Choose from above: ");
                    choice = in.next();

                    // if player wants to play
                    if (choice.equals("1")) {
                        try{
                        // invoke connect to server method
                        ConnectToServer();
                        }
                        catch(IOException e){
                            // if server is cloed print 
                            System.err.println("Sorry, the server is closed, please try again later..");
                            // continue to skip reading from closed server
                            continue;
                        }

                        // used to store player choice
                        String choose;
                        // used to store server messages after reading it
                        String res;

                        // write the player id to the server using DataOutputStream
                        dos.writeInt(IDs);
                        // write username to server
                        WriteToServer.println(name);
              
                        // round counter
                        int rounds = 1;
                         try{
                        while (rounds <= 3) {
                             
                            // read and print round number label
                            System.out.println("\n"+ReadFromServer.nextLine());
                            // ask player to choose  tool
                            System.out.println(ReadFromServer.nextLine());
                           
                            
                            do {
                                // player enters a vlaue
                                 System.out.print("-> ");
                                choose = tool.nextLine();

                                // if value is not matched to the provided tools then repeat
                                if (!choose.equals("1") && !choose.equals("2") && !choose.equals("3")) {
                                    System.out.println("Try [1:Rock 2:Paper 3:Scissor]:");
                                }
                            } while (!choose.equals("1") && !choose.equals("2") && !choose.equals("3"));
                            
                          
                            // write the tool choosed to the server to it can compare it with the other player
                            WriteToServer.println(choose);

                            // print your points
                            res = ReadFromServer.nextLine();
                            System.out.println("\n"+res);

                            // print enemy points
                            res = ReadFromServer.nextLine();
                            System.out.println(res);

                            // print who won or lose the round
                            res = ReadFromServer.nextLine();
                            System.out.println(res);
                            // increase the number of rounds
                            rounds++;
                      
                        }
                            // print who won the game
                         res = ReadFromServer.nextLine();
                        System.out.println("\n"+res);

                         }
                            catch(Exception e){
                                // if enemy forced to exit then will print
                                System.out.println("Enemy has disconnected");

                                 // return to game menu
                                break;
                            }
                         
                        // close scanner and printWriter
                        WriteToServer.close();
                        ReadFromServer.close();
                    

                    }

                    // if player want to see the games he won
                    else if (choice.equals("2")) {
                        do {

                     
                            // search in database where player was a winner
                            ResultSet r = stat.executeQuery("select * from gameplay where winner = '" + name + "'");
                            boolean rs;
                            rs = r.next();

                            // if found then
                            if (rs) {
                                
                                // print details of the games he won
                System.out.print("\n+--------+---------+--------+------------+\n" +
                                  "| GameID | Player2 | result  | Game date  |\n" +
                                  "+--------+---------+--------+------------+\n");
                                while (rs) {
                                    String  otherPlayer =r.getString("Player2");
                                    
                                    if (!otherPlayer.equals(name)){
                                    System.out.printf("%4s %9s  %11s %12s", r.getString("GameID"),
                                            otherPlayer, r.getString("Result"), r.getString("Gdate"));
                                    System.out.println("\n" + "+--------+---------+--------+------------+");
                                    rs = r.next();
                                    }

                                     else if (otherPlayer.equals(name)){

                                            System.out.printf("%4s %9s  %11s %12s", r.getString("GameID"),
                                            r.getString("Player1"), r.getString("Result"), r.getString("Gdate"));
                                            System.out.println("\n" + "+--------+---------+--------+------------+");
                                            rs = r.next();
                                            }

                 

                                }
                            } else {
                                // if not found then will print:
                                System.out.println("no records found..play harder!");
                            }
                            
                             // this will display how many games did the player win
                            r = stat.executeQuery("select Wins from player where username = '" + name + "'");
                            if (r.next()) {
                                int winsNum = r.getInt("Wins");

                                // if it is even then print with word games(plural) else print with word game(singular)
                                if (winsNum % 2 == 0) {
                                    System.out.println("You have won " + winsNum + " games");
                                } else {
                                    System.out.println("You have won " + winsNum + " game");
                                }
                            }

                            // ask to enter "E' if user wants to exit page
                            System.out.print("Press " + "E" + " to exit record page: ");
                            choice = in.next();

                            // if other than "E" then it will repeat the menu
                        } while (!choice.equalsIgnoreCase("E"));

                    }

                    // if user wants to see tutorial page
                    else if (choice.equals("3")) {

                        do {
                            // tutorial page
                            System.out.print("\n===== Rock Paper Scissors =====\n"
                                    + "* In RPS game you can play online with any player.\n" +
                                    "* You will play for THREE rounds.\n" +
                                    "* The one gets the highest points will win the game.\n" +
                                        Tutorial
                                    +
                                    "\n* Press " + "1" + " to choose Rock\n" +
                                    "* Press " + "2" + " to choose Paper\n" +
                                    "* Press " + "3" + " to choose Scissor\n" +

                                    "\nPress " + "E" + " to exit tutorial page: ");
                            // if other than "E" then it will repeat the menu
                            choice = in.next();

                            // repeat while input is not exit
                        } while (!choice.equalsIgnoreCase("E"));

                    }
                        // if player wants to exit program
                    else if (choice.equals("4")) {
                        // print thank you message
                        System.out.println("Thank you for trying our game ^_^");
                        // break game menu loop
                        break;
                    }

                    // repeat as long as user entery does not match the menu options
                } while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4"));

            }

            // if input is 4 or 3 in the main loop then exit loop
            else if (choice.equals("4") || choice.equals("3")) {
                // close Scanner tools
                in.close();
                tool.close();
                SignUp.close();
                login.close();


            // break main loop -exit program-
                break;
            }
        }

    }



    // check if the name is used or not to register
    private boolean checkReg(String name, PreparedStatement ps, Connection conn) throws SQLException {

        // select username entered
        ps = conn.prepareStatement("select * from player where username = ?");
        ps.setString(1, name);
        ps.execute();
        ResultSet r = ps.getResultSet();
        // if exists in database then return true
        if (r.next()) {
            return true;
        } 
        // if it does not exist then return false
        else {
            return false;
        }

    }

    // connect to the server method
    private void ConnectToServer() throws IOException {
        // connect to the server on prot 5000
        s = new Socket(InetAddress.getLocalHost(), 5000);
        
        // Output Input Streams for reading and writing into/from server
        InputStream in = s.getInputStream();
        OutputStream out = s.getOutputStream();
    
         dos = new DataOutputStream(out);

        WriteToServer = new PrintWriter(out, true);
        ReadFromServer = new Scanner(in);
        
        
        // read player count number frm server
        String Notify = ReadFromServer.nextLine();

        // if player count number is 1 then wait for player 2
        if(Notify.equals("1")){

            System.out.println("Wait for Player 2..");
        }
        else{
            // if player count number is 2 then notify that player one is connected
            System.out.println("player #" + 1 + " has connected");
        }

        
        // notify player 1 and 2 that player2 has joined the game
        String Notify2 = ReadFromServer.nextLine();

        System.out.println(Notify2);
       
    
    }

    // check if account is in database or not
    private boolean CheckLogin(String name, String pass, PreparedStatement ps, Connection conn) throws SQLException {
        ps = conn.prepareStatement("select ID from player where username = ? and password = ?");
        // select the id using the entered username and password
        ps.setString(1, name);
        ps.setString(2, pass);
        ps.execute();
        ResultSet r = ps.getResultSet();
        // if an id exists then
        if (r.next()) {
            // then
            return false;
        }

        else { // if it does not then
            return true;
        }
    }
 
    // generate ID for new player
    private static int generateID(Connection conn) throws SQLException {
        Statement stat = conn.createStatement();

        ResultSet rs = stat.executeQuery("select MAX(ID) as 'NEW_ID' FROM player;");
            //generate a new unique id for each player

            // if no ids in the database
        if (rs.next() == false) {
            // return 1 as a first ID in the player database
            return 1;
        }

        else {
            // else return the maximum id number and increase it by 1 then return it to the new player
            return rs.getInt("new_id") + 1;

        }
    }
}