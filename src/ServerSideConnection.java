
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.UUID;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class ServerSideConnection extends Thread {

    public String RockShape = "\n Rock \n     _______\n---'   ____)\n      (_____)\n      (_____)\n     (____)\n---.__(___)\n";
    public String PaperShape = "\n Paper \n      _______\n---'    ____)____\n           ______)\n          _______)\n         _______)\n---.__________)\n";
    public String ScissorShape = "\n Scissor \n     _______\n---'   ____)____\n          ______)\n       __________)\n      (____)\n---.__(___)\n";
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
    private Connection conn;
    private String choose1;
    private String choose2;
    private static int Players;
    private static int P1Points = 0;
    private static int P2Points = 0;

    ServerSideConnection(Socket s, Socket s2, int Players, Statement stat) {
        this.Player1 = s;
        this.Player2 = s2;
        this.Players = Players;
        this.conn = conn;
        this.stat = stat;

        try {

            InputStream in = Player1.getInputStream();
            InputStream in2 = Player2.getInputStream();
            OutputStream out = Player1.getOutputStream();
            OutputStream out2 = Player2.getOutputStream();

            WriteToPlayer1 = new PrintWriter(out);
            ReadFromPlayer1 = new Scanner(in);

            WriteToPlayer2 = new PrintWriter(out2);
            ReadFromPlayer2 = new Scanner(in2);

        } catch (Exception e) {
            System.err.println(e);
        }

    }

    @Override
    public void run() {

        try {

            String GID = UUID.randomUUID().toString().substring(2, 5);

            String P1name = "", P2name = "";
            P1name = ReadFromPlayer1.nextLine();
            P2name = ReadFromPlayer2.nextLine();

            int rounds = 1;
            while (rounds <= 3) {
                WriteToPlayer1.println("======= ROUND " + rounds + "=======");
                WriteToPlayer2.println("======= ROUND " + rounds + "=======");
                WriteToPlayer1.flush();
                WriteToPlayer2.flush();

                WriteToPlayer1.println("choose [rock: 1 paper: 2 scissor: 3]");
                WriteToPlayer2.println("choose [rock: 1 paper: 2 scissor: 3]");

                WriteToPlayer1.flush();
                WriteToPlayer2.flush();

                choose1 = ReadFromPlayer1.nextLine();
                choose2 = ReadFromPlayer2.nextLine();

                System.out.println("======== Room : "+GID+" ===========");

                if (choose1.equals(choose2)) {
                    WriteToPlayer1.println("Draw");
                    WriteToPlayer2.println("Draw");
                    System.out.println("Draw!!");
                    System.out.println(P1name + " Points: " + P1Points);
                    System.out.println(P2name + " Points: " + P2Points);
                    WriteToPlayer1.flush();
                    WriteToPlayer2.flush();

                    WriteToPlayer2.println("Your Points: " + P2Points);
                    WriteToPlayer1.println(" Your points: " + P1Points);
                    WriteToPlayer1.flush();
                    WriteToPlayer2.flush();

                    WriteToPlayer2.println("Enemy Points: " + P1Points);
                    WriteToPlayer1.println(" Enemy points: " + P2Points);

                    WriteToPlayer1.flush();
                    WriteToPlayer2.flush();

                }

                else if (choose1.equals("1") && choose2.equals("2")) {
                    WriteToPlayer1.println("Lose");
                    WriteToPlayer2.println("Win");
                    System.out.println(P2name + " win");
                    P2Points += 10;
                    System.out.println(P1name + " Points: " + P1Points);
                    System.out.println(P2name + " Points: " + P2Points);
                    WriteToPlayer1.flush();
                    WriteToPlayer2.flush();

                    WriteToPlayer2.println("Your Points: " + P2Points);
                    WriteToPlayer1.println(" Your points: " + P1Points);
                    WriteToPlayer1.flush();
                    WriteToPlayer2.flush();

                    WriteToPlayer2.println("Enemy Points: " + P1Points);
                    WriteToPlayer1.println(" Enemy points: " + P2Points);

                    WriteToPlayer1.flush();
                    WriteToPlayer2.flush();

                }

                else if (choose1.equals("1") && choose2.equals("3")) {

                    WriteToPlayer1.println("Win");
                    WriteToPlayer2.println("Lose");
                    System.out.println(P1name + "win");
                    P1Points += 10;
                    System.out.println(P1name + " Points: " + P1Points);
                    System.out.println(P2name + " Points: " + P2Points);
                    WriteToPlayer1.flush();
                    WriteToPlayer2.flush();

                    WriteToPlayer2.println("Your Points: " + P2Points);
                    WriteToPlayer1.println(" Your points: " + P1Points);
                    WriteToPlayer1.flush();
                    WriteToPlayer2.flush();

                    WriteToPlayer2.println("Enemy Points: " + P1Points);
                    WriteToPlayer1.println(" Enemy points: " + P2Points);

                    WriteToPlayer1.flush();
                    WriteToPlayer2.flush();

                }

                else if (choose1.equals("2") && choose2.equals("1")) {

                    WriteToPlayer1.println("Win");
                    WriteToPlayer2.println("Lose");
                    System.out.println(P1name + " win");
                    P1Points += 10;
                    System.out.println(P1name + " Points: " + P1Points);
                    System.out.println(P2name + " Points: " + P2Points);
                    WriteToPlayer1.flush();
                    WriteToPlayer2.flush();

                    WriteToPlayer2.println("Your Points: " + P2Points);
                    WriteToPlayer1.println(" Your points: " + P1Points);
                    WriteToPlayer1.flush();
                    WriteToPlayer2.flush();

                    WriteToPlayer2.println("Enemy Points: " + P1Points);
                    WriteToPlayer1.println(" Enemy points: " + P2Points);

                    WriteToPlayer1.flush();
                    WriteToPlayer2.flush();

                }

                else if (choose1.equals("2") && choose2.equals("3")) {
                    WriteToPlayer1.println("Lose");
                    WriteToPlayer2.println("Win");
                    System.out.println(P2name + " win");
                    P2Points += 10;
                    System.out.println(P1name + " Points: " + P1Points);
                    System.out.println(P2name + " Points: " + P2Points);
                    WriteToPlayer1.flush();
                    WriteToPlayer2.flush();

                    WriteToPlayer2.println("Your Points: " + P2Points);
                    WriteToPlayer1.println(" Your points: " + P1Points);
                    WriteToPlayer1.flush();
                    WriteToPlayer2.flush();

                    WriteToPlayer2.println("Enemy Points: " + P1Points);
                    WriteToPlayer1.println(" Enemy points: " + P2Points);

                    WriteToPlayer1.flush();
                    WriteToPlayer2.flush();

                }

                else if (choose1.equals("3") && choose2.equals("1")) {

                    WriteToPlayer1.println("Lose");
                    WriteToPlayer2.println("Win");
                    System.out.println(P2name + " win");
                    P2Points += 10;
                    System.out.println(P1name + " Points: " + P1Points);
                    System.out.println(P2name + " Points: " + P2Points);
                    WriteToPlayer1.flush();
                    WriteToPlayer2.flush();

                    WriteToPlayer2.println("Your Points: " + P2Points);
                    WriteToPlayer1.println(" Your points: " + P1Points);
                    WriteToPlayer1.flush();
                    WriteToPlayer2.flush();

                    WriteToPlayer2.println("Enemy Points: " + P1Points);
                    WriteToPlayer1.println(" Enemy points: " + P2Points);

                    WriteToPlayer1.flush();
                    WriteToPlayer2.flush();

                }

                else if (choose1.equals("3") && choose2.equals("2")) {
                    WriteToPlayer2.println("Lose");
                    WriteToPlayer1.println("Win");
                    WriteToPlayer1.flush();
                    WriteToPlayer2.flush();

                    System.out.println(P1name + " win");
                    P1Points += 10;
                    System.out.println(P1name + " Points: " + P1Points);
                    System.out.println(P2name + " Points: " + P2Points);

                    WriteToPlayer2.println("Your Points: " + P2Points);
                    WriteToPlayer1.println(" Your points: " + P1Points);
                    WriteToPlayer1.flush();
                    WriteToPlayer2.flush();

                    WriteToPlayer2.println("Enemy Points: " + P1Points);
                    WriteToPlayer1.println(" Enemy points: " + P2Points);

                    WriteToPlayer1.flush();
                    WriteToPlayer2.flush();

                }

                if (P1Points > P2Points) {
                    System.out.println(P1name + " Win  round " + rounds + "!!");
                    WriteToPlayer1.println("You Win ROUND " + rounds + "!!");
                    WriteToPlayer2.println("You Lose ROUND " + rounds + "!!");

                    WriteToPlayer1.flush();
                    WriteToPlayer2.flush();
                } else if (P2Points > P1Points) {
                    System.out.println(P2name + " Win  round " + rounds + "!!");
                    WriteToPlayer2.println("You Win ROUND " + rounds + "!!");
                    WriteToPlayer1.println("You Lose ROUND " + rounds + "!!");

                    WriteToPlayer1.flush();
                    WriteToPlayer2.flush();
                } else {
                    System.out.println("It's a Draw round");
                    WriteToPlayer1.println("TIED ROUND !!");
                    WriteToPlayer2.println("TIED ROUND !!");

                    WriteToPlayer1.flush();
                    WriteToPlayer2.flush();
                }

                rounds++;
            }
            System.out.println("======== Room : "+GID+" Closed ===========");

            if (P1Points > P2Points) {
                System.out.println("Player 1 Win the GAME!!");
                WriteToPlayer1.println("You Win GAME !!");
                WriteToPlayer2.println("You Lose GAME !!");

                WriteToPlayer1.flush();
                WriteToPlayer2.flush();
            } else if (P2Points > P1Points) {
                System.out.println("Player 2 Win the GAME !!");
                WriteToPlayer2.println("You Win the GAME !!");
                WriteToPlayer1.println("You Lose the GAME !!");

                WriteToPlayer1.flush();
                WriteToPlayer2.flush();
            } else {
                System.out.println("It's a Draw GAME !!");
                WriteToPlayer2.println("It's a Draw GAME !!");
                WriteToPlayer1.println("It's a Draw GAME !!");

                WriteToPlayer1.flush();
                WriteToPlayer2.flush();

            }

            if (P1Points > P2Points) {
                int winsNum = 0;
                ResultSet r = stat.executeQuery("select Wins from player where username = '" + P1name + "'");
                if (r.next()) {
                    winsNum = r.getInt("Wins");
                }

                winsNum += 1;
                stat.execute("update player set Wins =" + winsNum + " where username ='" + P1name + "'");
                stat.execute("insert into gameplay (GameID, Player1, Player2, Winner, result, Gdate) values ('" + GID
                        + "','" + P1name + "','" + P2name + "','" + P1name + "','" + P1Points + " - " + P2Points
                        + "', CURRENT_DATE()");

            } else if (P2Points > P1Points) {
                int winsNum = 0;
                ResultSet r = stat.executeQuery("select Wins from player where username = '" + P2name + "'");
                if (r.next()) {
                    winsNum = r.getInt("Wins");
                }

                winsNum += 1;
                stat.execute("update player set Wins =" + winsNum + " where username ='" + P2name + "'");

                stat.execute("insert into gameplay (GameID, Player1, Player2, Winner, result, Gdate) values ('" + GID
                        + "','" + P1name + "','" + P2name + "','" + P2name + "','" + P1Points + " - " + P2Points
                        + "', CURRENT_DATE())");

            } else {
                stat.execute("insert into gameplay (GameID, Player1, Player2, Winner, result, Gdate) values ('" + GID
                        + "','" + P1name + "','" + P2name + "','no winner' ,'" + P1Points + " - " + P2Points
                        + "', CURRENT_DATE())");

            }

            System.out.println("--------------------GAME ENDS---------------");

        } catch (Exception e) {
            System.err.println(e);
        } finally {
            try {

                Players = 0;
                P1Points = 0;
                P2Points = 0;
                Player1.close();
                Player2.close();

                System.out.println("Waiting for players to join the server..");

            } catch (IOException e) {
                System.err.println(e);
            }

        }

    }

    public static int getPlayerCount() {
        return Players;
    }

}