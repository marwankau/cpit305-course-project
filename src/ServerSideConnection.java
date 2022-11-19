

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class ServerSideConnection extends Thread {

  // data fields
    private Socket Player1;
    private Socket Player2;
    private PrintWriter WriteToPlayer1;
    private Scanner ReadFromPlayer1;
    private PrintWriter WriteToPlayer2;
    private Scanner ReadFromPlayer2;
    private Statement stat;
    private String choose1;
    private String choose2;
    private ResultSet r;


    // store clients sockets and statemnt variable in constructor
    ServerSideConnection(Socket s, Socket s2, Statement stat) {
        this.Player1 = s;
        this.Player2 = s2;
        this.stat = stat;


    }

    
    @Override
    public void run() {

        try {

 // get input and output streams from client's sockets
            InputStream in = Player1.getInputStream();
            InputStream in2 = Player2.getInputStream();
            
            OutputStream out = Player1.getOutputStream();
            OutputStream out2 = Player2.getOutputStream();
            
            DataInputStream dis = new DataInputStream(in);
            
              DataInputStream dis2 = new DataInputStream(in2);

        // make Scanners and printWriters for both players to read and write into/from the server
            WriteToPlayer1 = new PrintWriter(out,true);
            ReadFromPlayer1 = new Scanner(in);

            WriteToPlayer2 = new PrintWriter(out2,true);
            ReadFromPlayer2 = new Scanner(in2);
            
            // initiates points for each player
              int P1Points = 0;
              int P2Points = 0;
            
              // generates ID using UUID library that helps in generating unique IDs and only taking 3 chars from it
            String GID = UUID.randomUUID().toString().substring(2,5);
            
         
           
        // check if the id is already used or not using resultset to search in database

        // selecting GameID coulmn in database
            stat.execute("select GameID from gameplay");
             r = stat.getResultSet();
             // if there are stored values in the column then store it in variable x
            boolean x = r.next();

            // if there is next then it will continue looping
            while(x){
                // take first value in column and store it in id String variable
           String id = r.getString("GameID");
      
           // and then compare id  to the initiated GameID (GID)
               if( id.equals(GID)){
                // if true then will change the GID and give it another ID
               GID = UUID.randomUUID().toString().substring(1,4);
               // then will make resultSet get back to the first value of the column
                r.beforeFirst();
           } 
           // will read the next value exists
                x = r.next();
           }
   
           // read players IDs using DataInputsStreams
            int PID1 =  dis.readInt();
            int PID2 =  dis2.readInt();
          
            
            


            
            // read players names
            String P1name = "", P2name = "";
            P1name = ReadFromPlayer1.nextLine();
            P2name = ReadFromPlayer2.nextLine();

        // there will be three rounds only
        // rounds is a counter that count the rounds remain
            int rounds = 1;
            while (rounds <= 3) {
                // first will print round number
                WriteToPlayer1.println("======= ROUND " + rounds + "=======");
                WriteToPlayer2.println("======= ROUND " + rounds + "=======");
        
                // Second will ask the user to choose it's tool
                WriteToPlayer1.println("Choose [rock: 1 paper: 2 scissor: 3]:");
                WriteToPlayer2.println("Choose [rock: 1 paper: 2 scissor: 3]:");


               
                try{
                // read the players choices
                choose1 = ReadFromPlayer1.nextLine();
                choose2 = ReadFromPlayer2.nextLine();
                
                }

                catch(Exception e){

                    // if player one has input his choice and get null expectation 
                    if(choose1 != null){
                        // print that player 2 has disconnected
                System.out.println(P2name+" has disconnected");
                    // and give all points of player2 to player1
                      P1Points += P2Points ;
                      P2Points = 0;
                    }

                    // if player two has input his choice and get null expectation 
                    else if (choose2 != null) {
                 // print that player 1 has disconnected
                System.out.println(P1name+" has disconnected");

                // and give all points of player2 to player1
                     P2Points += P1Points ;
                      P1Points = 0;

                    }       
                    break;
                }

            // print id of the game or room into server output
                System.out.println("\n======== Room : "+GID+" ===========");

                // comparing tools

                // if inputs are the same
                if (choose1.equals(choose2)) {

                    // points will increases with 5 points for both players
                    P1Points += 5;
                    P2Points += 5;

                    // printing points
                    System.out.println(P1name + " Points: " + P1Points);
                    System.out.println(P2name + " Points: " + P2Points);
           

                    WriteToPlayer2.println("Your points: " + P2Points);
                    WriteToPlayer1.println("Your points: " + P1Points);
      

                    WriteToPlayer2.println("Enemy points: " + P1Points);
                    WriteToPlayer1.println("Enemy points: " + P2Points);
                    
                }

           

                
                // if player one is rock and player two is papper
                else if (choose1.equals("1") && choose2.equals("2")) {

                    // points will increase with 10 points for player2
                    P2Points += 10;

                    // printing points
                    System.out.println(P1name + " Points: " + P1Points);
                    System.out.println(P2name + " Points: " + P2Points);
        
                    WriteToPlayer2.println("Your points: " + P2Points);
                    WriteToPlayer1.println("Your points: " + P1Points);
       

                    WriteToPlayer2.println("Enemy points: " + P1Points);
                    WriteToPlayer1.println("Enemy points: " + P2Points);

                       

                }

                // if player one is rock and player two is Scissors
                else if (choose1.equals("1") && choose2.equals("3")) {

                    // points will increase with 10 points for player1
                    P1Points += 10;

                    // printing points 
                    System.out.println(P1name + " Points: " + P1Points);
                    System.out.println(P2name + " Points: " + P2Points);
           
                    WriteToPlayer2.println("Your points: " + P2Points);
                    WriteToPlayer1.println("Your points: " + P1Points);
            

                    WriteToPlayer2.println("Enemy points: " + P1Points);
                    WriteToPlayer1.println("Enemy points: " + P2Points);

                

                }

                // if player two is rock and player one is paper
                else if (choose1.equals("2") && choose2.equals("1")) {

                  // points will increase with 10 points for player1
                    P1Points += 10;
                    System.out.println(P1name + " Points: " + P1Points);
                    System.out.println(P2name + " Points: " + P2Points);
            
                    // printing points 
                    WriteToPlayer2.println("Your points: " + P2Points);
                    WriteToPlayer1.println("Your points: " + P1Points);
            
                    WriteToPlayer2.println("Enemy points: " + P1Points);
                    WriteToPlayer1.println("Enemy points: " + P2Points);

      

                }

                // if player one is paper and player two is Scissors
                else if (choose1.equals("2") && choose2.equals("3")) {

                // points will increase with 10 points for player2
                    P2Points += 10;


                    // printing points
                    System.out.println(P1name + " Points: " + P1Points);
                    System.out.println(P2name + " Points: " + P2Points);
   

                    WriteToPlayer2.println("Your points: " + P2Points);
                    WriteToPlayer1.println("Your points: " + P1Points);
        
                    WriteToPlayer2.println("Enemy points: " + P1Points);
                    WriteToPlayer1.println("Enemy points: " + P2Points);

          

                }

                // if player two is rock and player one is Scissors
                else if (choose1.equals("3") && choose2.equals("1")) {

                    // points will increase with 10 points for player2
                    P2Points += 10;

                    // printing points
                    System.out.println(P1name + " Points: " + P1Points);
                    System.out.println(P2name + " Points: " + P2Points);
  

                    WriteToPlayer2.println("Your points: " + P2Points);
                    WriteToPlayer1.println("Your points: " + P1Points);
       

                    WriteToPlayer2.println("Enemy points: " + P1Points);
                    WriteToPlayer1.println("Enemy points: " + P2Points);

  

                }

                // if player two is paper and player one is Scissors
                else if (choose1.equals("3") && choose2.equals("2")) {

                    // points will increase with 10 points for player1
                    P1Points += 10;

                     // printing points
                    System.out.println(P1name + " Points: " + P1Points);
                    System.out.println(P2name + " Points: " + P2Points);

                    WriteToPlayer2.println("Your points: " + P2Points);
                    WriteToPlayer1.println("Your points: " + P1Points);
    

                    WriteToPlayer2.println("Enemy points: " + P1Points);
                    WriteToPlayer1.println("Enemy points: " + P2Points);

              

                }

                // compare who is the winner based on the highest points
                
                 
                if (P1Points > P2Points) {
                    // print the name of the winner and the number of the round in server otput
                    System.out.println(P1name + " Wins  round " + rounds + "!!");

                    // write to the winner player
                    WriteToPlayer1.println("You Win ROUND " + rounds + "!!");
                    //write to the loser player
                    WriteToPlayer2.println("You Lose ROUND " + rounds + "!!");

         
                } else if (P2Points > P1Points) {
                    System.out.println(P2name + " Wins  round " + rounds + "!!");
                    WriteToPlayer2.println("You Win ROUND " + rounds + "!!");
                    WriteToPlayer1.println("You Lose ROUND " + rounds + "!!");

                    
                } else {
                    // if points are equal then server will print draw
                    System.out.println("It's a Draw round");
                    // and send to players that it is a tied round
                    WriteToPlayer1.println("TIED ROUND !!");
                    WriteToPlayer2.println("TIED ROUND !!");

               
                }
            // after comparing and knowing the winner the program will increase the counter of rounds to move to the next round
                rounds++;
            }

            // space 
            System.out.println("\n");

            // once the loop finishes it will compare the points and print and send to player who won the game and who lose it.
            if (P1Points > P2Points) {
              
                System.out.println(P1name+" Wins the GAME !!");
                WriteToPlayer1.println("You Win GAME !!");
                WriteToPlayer2.println("You Lose GAME !!");

       
            } else if (P2Points > P1Points) {
                System.out.println(P2name+" Wins the GAME !!");
                WriteToPlayer2.println("You Win the GAME !!");
                WriteToPlayer1.println("You Lose the GAME !!");

               
            } else {
                System.out.println("It's a Draw GAME !!");
                WriteToPlayer2.println("It's a Draw GAME !!");
                WriteToPlayer1.println("It's a Draw GAME !!");

              

            }

            // printig that the game has finished  
            System.out.println("======== Room : "+GID+" Closed ===========\n");


            // updating the player database by increasing the winner's Wins attribute and store it.
            if (P1Points > P2Points) {
                int winsNum = 0;
                 r = stat.executeQuery("select Wins from player where username = '" + P1name + "'");
                if (r.next()) {
                    winsNum = r.getInt("Wins");
                }

                winsNum += 1;
                stat.executeUpdate("update player set Wins =" + winsNum + " where username ='" + P1name + "'");

                // insertion of the game details
                stat.executeUpdate("insert into gameplay (GameID, Player1, Player2, Winner, result, Gdate) values ('" + GID
                                   + "','" + P1name + "','" + P2name + "','" + P1name + "','" + P1Points + " - " + P2Points
                        + "', CURRENT_DATE())");

                    // insertion if the 2 players were playing from the same account
                        if(PID1==PID2){
                            stat.executeUpdate("insert into game_player (Game_ID, PID) values ('"+GID+"',"+PID1+")");
                         }else{
                             // insertion if the 2 players were playing from the diffrenet account
                            stat.executeUpdate("insert into game_player (Game_ID, PID) values ('"+GID+"',"+PID1+")");
                            stat.executeUpdate("insert into game_player (Game_ID, PID) values ('"+GID+"',"+PID2+")");
                              }
                    
                


            } 
            
            // updating the player database by increasing the winner's Wins attribute and store it.
            else if (P2Points > P1Points) {
                int winsNum = 0;
                 r = stat.executeQuery("select Wins from player where username = '" + P2name + "'");
                 
                if (r.next()) {
                    winsNum = r.getInt("Wins");
                }
             

                winsNum += 1;

                stat.executeUpdate("update player set Wins =" + winsNum + " where username ='" + P2name + "'");

                stat.executeUpdate("insert into gameplay (GameID, Player1, Player2, Winner, result, Gdate) values ('" + GID
                        + "','" + P1name + "','" + P2name + "','" + P2name + "','" + P1Points + " - " + P2Points
                            + "', CURRENT_DATE())");
                
                 // insertion if the 2 players were playing from the same account
                        if(PID1==PID2){
                            stat.executeUpdate("insert into game_player (Game_ID, PID) values ('"+GID+"',"+PID1+")");
                         }else{

                            // insertion if the 2 players were playing from the diffrenet account
                            stat.executeUpdate("insert into game_player (Game_ID, PID) values ('"+GID+"',"+PID1+")");
                            stat.executeUpdate("insert into game_player (Game_ID, PID) values ('"+GID+"',"+PID2+")");
                              }
                    
              
            } else {
                        // insertion if there is no winner
                stat.executeUpdate("insert into gameplay (GameID, Player1, Player2, Winner, result, Gdate) values ('" + GID
                        + "','" + P1name + "','" + P2name + "','no winner' ,'" + P1Points + " - " + P2Points
                        + "', CURRENT_DATE())");
                
                
                        // insertion if the 2 players were playing from the same account
                        if(PID1==PID2){
                            stat.executeUpdate("insert into game_player (Game_ID, PID) values ('"+GID+"',"+PID1+")");
                         }else{
                         // insertion if the 2 players were playing from the diffrenet account
                            stat.executeUpdate("insert into game_player (Game_ID, PID) values ('"+GID+"',"+PID1+")");
                            stat.executeUpdate("insert into game_player (Game_ID, PID) values ('"+GID+"',"+PID2+")");
                              } 


            }


            // set defualt  value for points    
                P1Points = 0;
                P2Points = 0;

            

        } 
        catch (SQLException e) {
            System.err.println("Somthing wrong with the database!");
            System.err.println(e);
        } catch (IOException e) {
            System.err.println("Connection problem");
            System.err.println(e);
        } 
        
        
        finally {
            try {
             
             // close tools
                Player1.close();
                Player2.close();
                ReadFromPlayer1.close();
                ReadFromPlayer2.close();
                WriteToPlayer1.close();
                WriteToPlayer2.close();

               

            } catch (IOException e) {
                System.err.println(e);
            } 

        }

    }
    
    



}
