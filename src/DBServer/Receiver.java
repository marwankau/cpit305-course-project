package DBServer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Receiver extends Thread {
   static Socket socket;
    static Connection conn;
    public Receiver(Socket socket,Connection conn) {
        this.socket = socket;
        this.conn=conn;
       
      }
     @Override
     public void run() {
     

           ReceiveData(conn);

       

       
     }
     private static void  listBooks()  {
       
        try  {
            OutputStream out = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out, true);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM books b ;");
            while(rs.next()){
                int id = rs.getInt("book_id");
                String name = rs.getString("bookName");
                int isa=rs.getInt("isAvailable");
                String sec = rs.getString("bookSec");
     
                writer.printf("%10d  %20s  %20s  %20s\n", id, name, sec,isa);

            }
        }  catch(IOException e){}
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private void sendresult(String msg) {
        
        if(msg.equalsIgnoreCase("1")){

            listBooks();
        }

       
    
      
        
        
    }
    private void ReceiveData(Connection conn) {
        InputStream in;
        try {
          in = socket.getInputStream();
          Scanner recScanner = new Scanner(in);
          
          String line;
    
          while (true) {
            line = recScanner.nextLine();
            if(line.equalsIgnoreCase("1")){
                System.out.println(line);
                sendresult(line);
            }
            
           
      
            else if (line.equalsIgnoreCase("bye")) break;
          }
    
          socket.shutdownInput();
    
        } catch (IOException e) {
        }
    }

      }
  
   

