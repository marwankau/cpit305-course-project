package App;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(1000);

        System.out.println("server waiting for connection...");


//        db con = new db();
//        con.DBconnection();
//        try {
//            con.deliverDB();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


        while (true){

            Socket socket = null;

            try {
                socket = server.accept();
                System.out.println("A new connection identified : " + socket);

                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                db con= new db();
                con.DBconnection();
                System.out.println("Thread assigned");
                con.deliverDB();

                Thread tThread = new ClientHandler(socket, reader , writer,con,objectInputStream);

                tThread.start();

            }catch (Exception e){
                socket.close();
                e.printStackTrace();
            }






//            Socket socket = server.accept();
//
//            //new Sender(socket).start();
//            new Receiver(socket).receiveOrderList();
//            //new Receiver(socket).start();
        }

    }
}
