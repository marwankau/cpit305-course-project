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


    private static PrintWriter WriteToPlayer1;
    private static PrintWriter WriteToPlayer2;
    private static ServerSideConnection ssc;

    private static Socket s2 ;
    private static Socket s ;

    public static void main(String[] args) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cpit305-project", "root", "moe123");
        Statement stat = conn.createStatement();

        try {
        ServerSocket server = new ServerSocket(5000);
        System.out.println("server connected..");

        s = new Socket();
        s2 = new Socket();
       
      
            while (!server.isClosed()) {

           
                    s = server.accept();

                    OutputStream out = s.getOutputStream();

                    WriteToPlayer1 = new PrintWriter(out, true);
                    WriteToPlayer1.println("1");


                    s2 = server.accept();

                    OutputStream out2 = s2.getOutputStream();
                    WriteToPlayer2 = new PrintWriter(out2, true);
                    WriteToPlayer2.println("2");
                    WriteToPlayer1.println("player #" + 2 + " has connected");
                    WriteToPlayer2.println("player #" + 2 + " has connected");



                    System.out.println("New 2 players are connected lets start game..");
                    
                  
                     ssc = new ServerSideConnection(s, s2, stat); 
                     ssc.start();
                                     
            }
            server.close();

                    

        } catch (IOException e) {
            System.out.println(e);

        }

    }

}


