package DBServer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Sender extends Thread{

    Socket socket;
    Connection conn;

    public Sender(Socket socket, Connection conn) {
      this.socket = socket;
      this.conn=conn;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        String line;
        InputStream in;
        
      Scanner recScanner ;
        try {
            in = socket.getInputStream();
             recScanner = new Scanner(in);
            OutputStream out = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out, true);
            while(true){
                line=recScanner.next();
        if(line.equalsIgnoreCase("1")){

    while (true) {
        Statement stmt = (Statement) conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM books b ");
        while (result.next()) {
         int id = result.getInt("book_id");
         String name = result.getString("bookName");
         int isa=result.getInt("isAvailable");
         String sec = result.getString("bookSec");
    
         writer.printf("%10d  %20s  %20s  %20s\n", id, name, sec,isa);
    
       }
socket.shutdownOutput();
  
}
}


                else if (line.equalsIgnoreCase("exit")) break;
                socket.shutdownOutput();
        
            }
          


            
      
           
            
          } catch (IOException e) {}
          catch(SQLException e){}

    }
}
