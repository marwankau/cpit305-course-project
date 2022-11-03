import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RPSGame {

    private static int Players = 0;

    private static PrintWriter WriteToPlayer1;
    private static PrintWriter WriteToPlayer2;
    private static ServerSideConnection ssc;

    static Socket s2 = new Socket();
    static Socket s = new Socket();

public static void main(String[] args) throws IOException, SQLException {

    
    Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cpit305-project", "root", "moe123");
    Statement stat = conn.createStatement();
   

    
    
    ServerSocket server = new ServerSocket(5000);
    System.out.println("server connected..");
    try  {
        while(!server.isClosed()){

            if(Players <= 1){
          s = server.accept();
          Players++;
          OutputStream out =  s.getOutputStream();
 
         
         WriteToPlayer1 = new PrintWriter(out,true);
         WriteToPlayer1.println("player #"+1+" has connected"); 
         WriteToPlayer1.println("Wait for Player2..");
         
          s2 = server.accept();
         
          Players++;
          OutputStream out2 =  s2.getOutputStream();
 
         WriteToPlayer2 = new PrintWriter(out2,true);
         WriteToPlayer2.println("player #"+1+" has connected"); 
         WriteToPlayer2.println("player #"+2+" has connected"); 
  
         System.out.println("2 are connected lets start game");
 
          ServerSideConnection ssc = new ServerSideConnection(s, s2, Players, stat);
             ssc.start();
             }
            else{
                 Players = ssc.getPlayerCount();
                 if(Players == 0){
                     s.close();
                     s2.close();
                 }
                 
 
             }
    }

     catch (Exception e) {
            System.out.println(e);
        
    }



}



 
     
}
