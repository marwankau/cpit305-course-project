
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class ServerSideConnection extends Thread {

    /**
     *
     */

    /**
     *
     */
    private Socket Player1;
    private Socket Player2;
    private PrintWriter WriteToPlayer1;
    private Scanner ReadFromPlayer1;
    private PrintWriter WriteToPlayer2;
    private Scanner ReadFromPlayer2;
    private Statement stat;
    private String choose1;
    private String choose2;
    private static int P1Points = 0;
    private static int P2Points = 0;

    ServerSideConnection(Socket s, Socket s2, Statement stat) {
        this.Player1 = s;
        this.Player2 = s2;
        this.stat = stat;


    }

    @Override
    public void run() {

        try {


            InputStream in = Player1.getInputStream();
            InputStream in2 = Player2.getInputStream();
            
            OutputStream out = Player1.getOutputStream();
            OutputStream out2 = Player2.getOutputStream();
            
            DataInputStream dis = new DataInputStream(in);
            
              DataInputStream dis2 = new DataInputStream(in2);
            

            WriteToPlayer1 = new PrintWriter(out,true);
            ReadFromPlayer1 = new Scanner(in);

            WriteToPlayer2 = new PrintWriter(out2,true);
            ReadFromPlayer2 = new Scanner(in2);
            
            


            int GID = generateID(stat);
   
            int PID1 =  dis.readInt();
            int PID2 =  dis2.readInt();
            
            


            

            String P1name = "", P2name = "";
            P1name = ReadFromPlayer1.nextLine();
            P2name = ReadFromPlayer2.nextLine();

            int rounds = 1;
            while (rounds <= 3) {
                WriteToPlayer1.println("======= ROUND " + rounds + "=======");
                WriteToPlayer2.println("======= ROUND " + rounds + "=======");
        

                WriteToPlayer1.println("Choose [rock: 1 paper: 2 scissor: 3]:");
                WriteToPlayer2.println("Choose [rock: 1 paper: 2 scissor: 3]:");

               
                try{
                choose1 = ReadFromPlayer1.nextLine();
                choose2 = ReadFromPlayer2.nextLine();
                }
                catch(Exception e){
                    if(choose1 != null){
                System.out.println(P2name+" has disconnected");
                    
                      P1Points += P2Points ;
                      P2Points = 0;
                    }
                    else if (choose2 != null) {
                System.out.println(P1name+" has disconnected");
                     P2Points += P1Points ;
                      P1Points = 0;

                    }       
                    break;
                }
                System.out.println("\n======== Room : "+GID+" ===========");

                if (choose1.equals(choose2)) {

                    System.out.println(P1name + " Points: " + P1Points);
                    System.out.println(P2name + " Points: " + P2Points);
           

                    WriteToPlayer2.println("Your points: " + P2Points);
                    WriteToPlayer1.println("Your points: " + P1Points);
      

                    WriteToPlayer2.println("Enemy points: " + P1Points);
                    WriteToPlayer1.println("Enemy points: " + P2Points);

           

                }

                else if (choose1.equals("1") && choose2.equals("2")) {
       
                    P2Points += 10;
                    System.out.println(P1name + " Points: " + P1Points);
                    System.out.println(P2name + " Points: " + P2Points);
        

                    WriteToPlayer2.println("Your points: " + P2Points);
                    WriteToPlayer1.println("Your points: " + P1Points);
       

                    WriteToPlayer2.println("Enemy points: " + P1Points);
                    WriteToPlayer1.println("Enemy points: " + P2Points);

          

                }

                else if (choose1.equals("1") && choose2.equals("3")) {

                    P1Points += 10;
                    System.out.println(P1name + " Points: " + P1Points);
                    System.out.println(P2name + " Points: " + P2Points);
           
                    WriteToPlayer2.println("Your points: " + P2Points);
                    WriteToPlayer1.println("Your points: " + P1Points);
            

                    WriteToPlayer2.println("Enemy points: " + P1Points);
                    WriteToPlayer1.println("Enemy points: " + P2Points);

                

                }

                else if (choose1.equals("2") && choose2.equals("1")) {

                    P1Points += 10;
                    System.out.println(P1name + " Points: " + P1Points);
                    System.out.println(P2name + " Points: " + P2Points);
            

                    WriteToPlayer2.println("Your points: " + P2Points);
                    WriteToPlayer1.println("Your points: " + P1Points);
            
                    WriteToPlayer2.println("Enemy points: " + P1Points);
                    WriteToPlayer1.println("Enemy points: " + P2Points);

      

                }

                else if (choose1.equals("2") && choose2.equals("3")) {

                    P2Points += 10;
                    System.out.println(P1name + " Points: " + P1Points);
                    System.out.println(P2name + " Points: " + P2Points);
   

                    WriteToPlayer2.println("Your points: " + P2Points);
                    WriteToPlayer1.println("Your points: " + P1Points);
        
         

                    WriteToPlayer2.println("Enemy points: " + P1Points);
                    WriteToPlayer1.println("Enemy points: " + P2Points);

          

                }

                else if (choose1.equals("3") && choose2.equals("1")) {

                    P2Points += 10;
                    System.out.println(P1name + " Points: " + P1Points);
                    System.out.println(P2name + " Points: " + P2Points);
  

                    WriteToPlayer2.println("Your points: " + P2Points);
                    WriteToPlayer1.println("Your points: " + P1Points);
       

                    WriteToPlayer2.println("Enemy points: " + P1Points);
                    WriteToPlayer1.println("Enemy points: " + P2Points);

  

                }

                else if (choose1.equals("3") && choose2.equals("2")) {

                    P1Points += 10;
                    System.out.println(P1name + " Points: " + P1Points);
                    System.out.println(P2name + " Points: " + P2Points);

                    WriteToPlayer2.println("Your points: " + P2Points);
                    WriteToPlayer1.println("Your points: " + P1Points);
    

                    WriteToPlayer2.println("Enemy points: " + P1Points);
                    WriteToPlayer1.println("Enemy points: " + P2Points);

              

                }

                if (P1Points > P2Points) {
                    System.out.println(P1name + " Wins  round " + rounds + "!!");
                    WriteToPlayer1.println("You Win ROUND " + rounds + "!!");
                    WriteToPlayer2.println("You Lose ROUND " + rounds + "!!");

         
                } else if (P2Points > P1Points) {
                    System.out.println(P2name + " Wins  round " + rounds + "!!");
                    WriteToPlayer2.println("You Win ROUND " + rounds + "!!");
                    WriteToPlayer1.println("You Lose ROUND " + rounds + "!!");

                    
                } else {
                    System.out.println("It's a Draw round");
                    WriteToPlayer1.println("TIED ROUND !!");
                    WriteToPlayer2.println("TIED ROUND !!");

               
                }

                rounds++;
            }

            System.out.println("\n");
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

            System.out.println("======== Room : "+GID+" Closed ===========\n");


            if (P1Points > P2Points) {
                int winsNum = 0;
                ResultSet r = stat.executeQuery("select Wins from player where username = '" + P1name + "'");
                if (r.next()) {
                    winsNum = r.getInt("Wins");
                }

                winsNum += 1;
                stat.executeUpdate("update player set Wins =" + winsNum + " where username ='" + P1name + "'");
                stat.executeUpdate("insert into gameplay (GameID, Player1, Player2, Winner, result, Gdate) values (" + GID
                        + ",'" + P1name + "','" + P2name + "','" + P1name + "','" + P1Points + " - " + P2Points
                        + "', CURRENT_DATE())");
                
                
            stat.executeUpdate("insert into game_player (Game_ID, PID) values ("+GID+","+PID1+")");
            stat.executeUpdate("insert into game_player (Game_ID, PID) values ("+GID+","+PID2+")");

            } else if (P2Points > P1Points) {
                int winsNum = 0;
                ResultSet r = stat.executeQuery("select Wins from player where username = '" + P2name + "'");
                if (r.next()) {
                    winsNum = r.getInt("Wins");
                }

                winsNum += 1;
                stat.executeUpdate("update player set Wins =" + winsNum + " where username ='" + P2name + "'");

                stat.executeUpdate("insert into gameplay (GameID, Player1, Player2, Winner, result, Gdate) values (" + GID
                        + ",'" + P1name + "','" + P2name + "','" + P2name + "','" + P1Points + " - " + P2Points
                        + "', CURRENT_DATE())");
                
                
            stat.executeUpdate("insert into game_player (Game_ID, PID) values ("+GID+","+PID1+")");
            stat.executeUpdate("insert into game_player (Game_ID, PID) values ("+GID+","+PID2+")");

            } else {
                stat.executeUpdate("insert into gameplay (GameID, Player1, Player2, Winner, result, Gdate) values (" + GID
                        + ",'" + P1name + "','" + P2name + "','no winner' ,'" + P1Points + " - " + P2Points
                        + "', CURRENT_DATE())");
                
                
            stat.executeUpdate("insert into game_player (Game_ID, PID) values ("+GID+","+PID1+")");
            stat.executeUpdate("insert into game_player (Game_ID, PID) values ("+GID+","+PID2+")");

            }


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

                P1Points = 0;
                P2Points = 0;
             
            
               
                Player1.close();
                Player2.close();
                WriteToPlayer1.close();
                WriteToPlayer2.close();
                ReadFromPlayer1.close();
                ReadFromPlayer2.close();

               

            } catch (IOException e) {
                System.err.println(e);
            }

        }

    }
    
        private synchronized static int generateID(Statement stat) throws SQLException {
        
        ResultSet rs = stat.executeQuery("select MAX(GameID) as 'NEW_ID' FROM GamePlay;");

        if (rs.next() == false) {
            return 1;
        }

        else {
            return rs.getInt("new_id") + 1;

        }
    }



}