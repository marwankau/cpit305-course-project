package DBServer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBServer {
    static Connection conn;
    static ServerSocket server;
    public static void main(String[] args) throws Exception {
      conn = DB.getConnection();
         server = new ServerSocket(2000);
         while (true) {

            Socket socket = server.accept();
             // TODO: make server accept login check for several user in the same time
            new Login(socket).start();
          
           

        }
    }


    
    public static String getFullName(String username, Connection conn) {
        String name="";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = conn.prepareStatement("SELECT * FROM `users` WHERE `username` LIKE ? ;");

            ps.setString(1, username);
             if(ps.execute()){
            rs = ps.getResultSet();
            
            while(rs.next()){
                int id=rs.getInt("userID");
                String usr = rs.getString("username");
                String pass = rs.getString("password");
                 String role=rs.getString("role");
                  name += rs.getString("fullName");

               
            } 
            
    
          
        return name;
                             
          
                }
    
    else{
         return name+="no name";
    }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  catch (Exception a) {
            a.printStackTrace();
        } finally {
            if(rs != null){
                 try{
                      rs.close();
                 } catch(Exception e){
                     e.printStackTrace();
                 }
            }
            if(ps != null){
                try{
                    ps.close();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }


       return name;
    }




    public static String getRole(String username, Connection conn) {

        String role="";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = conn.prepareStatement("SELECT * FROM `users` WHERE `username` LIKE ? ;");

            ps.setString(1, username);
             if(ps.execute()){
             rs = ps.getResultSet();
            
            while(rs.next()){
                int id=rs.getInt("userID");
                String usr = rs.getString("username");
                String pass = rs.getString("password");
                 role+=rs.getString("role");
                 String name = rs.getString("fullName");
               
               
            } 
            
    
          
        return role;
                             
          
                }
    
    else{
         return role+="no role";
    }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        catch (Exception a) {
            a.printStackTrace();
        } finally {
            if(rs != null){
                 try{
                      rs.close();
                 } catch(Exception e){
                     e.printStackTrace();
                 }
            }
            if(ps != null){
                try{
                    ps.close();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }


       return role;
    }

    public static int getID(String username, Connection conn) {
        int id=-1;
         PreparedStatement ps=null;
         ResultSet rs=null;
         try {
             ps = conn.prepareStatement("SELECT * FROM `users` WHERE `username` LIKE ? ;");
 
             ps.setString(1, username);
              if(ps.execute()){
              rs = ps.getResultSet();
             
             while(rs.next()){
                  id=rs.getInt("userID");
                 String usr = rs.getString("username");
                 String pass = rs.getString("password");
                 String role=rs.getString("role");
                  String name = rs.getString("fullName");
               
             } 
             
     
         
         return id;
                              
           
                 }
     
     else{
          return id;
     }
         } catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }

         catch (Exception a) {
            a.printStackTrace();
        } finally {
            if(rs != null){
                 try{
                      rs.close();
                 } catch(Exception e){
                     e.printStackTrace();
                 }
            }
            if(ps != null){
                try{
                    ps.close();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
 
 
        return id;
     }
    public static synchronized boolean checkLogin(String username, String password, Connection conn ) {
        boolean revalue=false;

        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = conn.prepareStatement("SELECT * FROM `users` WHERE `username` LIKE ? ;");
        
        
            ps.setString(1, username);
           
           
           if(ps.execute()){
             rs = ps.getResultSet();
            
            while(rs.next()){
    
                int id=rs.getInt("userID");
                String usr = rs.getString("username");
                String pass = rs.getString("password");
                String role=rs.getString("role");
                String name = rs.getString("fullName");
                             if(username.equals(usr) && password.equals(pass)){
                    revalue=true;
                    break;
                }
            } 
        }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception a) {
            a.printStackTrace();
        } finally {
            if(rs != null){
                 try{
                      rs.close();
                 } catch(Exception e){
                     e.printStackTrace();
                 }
            }
            if(ps != null){
                try{
                    ps.close();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }

        return revalue;
    }
}
