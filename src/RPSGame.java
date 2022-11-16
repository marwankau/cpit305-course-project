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


    // data fileds
    private static PrintWriter WriteToPlayer1;
    private static PrintWriter WriteToPlayer2;
    private static ServerSideConnection ssc;

    private static Socket s2 ;
    private static Socket s ;

    public static void main(String[] args) throws SQLException {

        // connecting to database
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cpit305-project", "root", "moe123");
        Statement stat = conn.createStatement();

        //start accepting clients using socket object 
        try {
            // run server on port 5000
        ServerSocket server = new ServerSocket(5000);
        // notify users that sever is working
        System.out.println("server connected..");

        s = new Socket();
        s2 = new Socket();
       
       // the server will keep accepting clients until the server is closed
            while (!server.isClosed()) {

           // accept first client
                    s = server.accept();

                    // OutPutStreams will help to make server write to the clients
                    OutputStream out = s.getOutputStream();

                    WriteToPlayer1 = new PrintWriter(out, true);
                    // sending first player count to player one
                    WriteToPlayer1.println("1");

                //  accept second player
                    s2 = server.accept();

                    
                    OutputStream out2 = s2.getOutputStream();
                    WriteToPlayer2 = new PrintWriter(out2, true);

                  // sending second player count to player two 
                    WriteToPlayer2.println("2");

                // server notify to players that player number 2 has joined the game
                    WriteToPlayer1.println("player #" + 2 + " has connected");
                    WriteToPlayer2.println("player #" + 2 + " has connected");


                    // server will print this when each 2 palyers joined a game
                    System.out.println("New 2 players are connected lets start game..");
                    
                  
                    // thread that will take only two players and Statement variable to store and fetch data from database
                     ssc = new ServerSideConnection(s, s2, stat); 
                     ssc.start();
                                     
            }

            // close server
            server.close();

                    

        } catch (IOException e) {
            System.out.println(e);

        }

    }

}


