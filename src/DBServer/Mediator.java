package DBServer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Statement;

public class Mediator extends Thread{
private Socket socket;
private User x;
DataOutputStream dos ;
DataInputStream dis ;
static Connection conn=DB.getConnection();
    public Mediator(Socket socket, User x, DataInputStream dis, DataOutputStream dos) {
        this.socket=socket;
        this.x=x;
        this.dis=dis;
        this.dos=dos;
    }
    @Override
    public void run() {
        try {
           
            
            while (true) {
                String line = dis.readUTF();

                //for admin//////////
                if(x.getRole().equalsIgnoreCase("admin")){
                    if(line.equalsIgnoreCase("1")){
                        InsertWorker(dis);



                    }
                    else if(line.equalsIgnoreCase("2")){
                        int id =dis.readInt();
                        saerchByIDWorker(id);
                        String asn=dis.readUTF();
                        if (asn.equalsIgnoreCase("y")){

                            removeWorker(id);
                        }else{
                            break;
                        }



                    }
                    
                    else if(line.equalsIgnoreCase("3")){
                        
                        listWorker();



                    }else if (line.equals("0")) {
                        socket.close();
                        break;
                    }

                }
                //for manager////////////////
              else if(x.getRole().equalsIgnoreCase("manager")){

                    if(line.equalsIgnoreCase("1")){
                        Insertbooks();



                    }else if(line.equalsIgnoreCase("2")){
                        
                        int id =dis.readInt();
                        saerchByBookID(id);
                        String asn=dis.readUTF();
                        if (asn.equalsIgnoreCase("y")){

                            removeBook(id);
                        }else{
                            break;
                        }



                    }
                    
                    else if(line.equalsIgnoreCase("3")){
                        
                        listbooks();



                    }else if (line.equals("0")) {
                        socket.close();
                        break;}



                }
                //for employee/////////////////////
                else  if(x.getRole().equalsIgnoreCase("emp")){
                    if(line.equalsIgnoreCase("1")){
                        int id =dis.readInt();
                        saerchByBookID(id);
                        String asn=dis.readUTF();
                        if (asn.equalsIgnoreCase("y")){

                            BorrowBook(id);
                        }else{
                            break;
                        }
                    } else if(line.equalsIgnoreCase("2")){
                       
                            int id =dis.readInt();
                            saerchByBookID(id);
                            String asn=dis.readUTF();
                            if (asn.equalsIgnoreCase("y")){
    
                                Returnbooks(id);
                            }else{
                                break;
                            }
                        
                    
                }      else if(line.equalsIgnoreCase("3")){
                    listbooksAvailable();
                    }  else if(line.equalsIgnoreCase("4")){
                        listbooksUnavailable();
                    }  else if (line.equals("0")) {
                        socket.close();
                        break;

                    }

                }
            
                 
                
               
            
            
            }
              

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
    }

    /////list all book unavailabel//////////
    private void listbooksUnavailable() {
        String x="";
        Statement st=null;
        ResultSet rs=null;
        try {
            st=  conn.createStatement();
            rs =  st.executeQuery("SELECT * FROM books b WHERE isAvailable=0;");
            while (rs.next()) {
                int id = rs.getInt("book_id");
                String name = rs.getString("bookName");
                String sec = rs.getString("bookSec");
                int isA = rs.getInt("isAvailable");
                
    
               x+= String.format("%5d   %12s   %30s %5d \n",id,name,sec,isA );
                
    
            }
            dos.writeUTF( x);
        } catch (IOException e) {
            // TODO: handle exception
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            if(rs != null){
                 try{
                      rs.close();
                 } catch(Exception e){
                     e.printStackTrace();
                 }
                }
            }
    }
    /////list all book availabel//////////
    private void listbooksAvailable() {

        String x="";
        Statement st=null;
        ResultSet rs=null;
        try {
            st=  conn.createStatement();
            rs =  st.executeQuery("SELECT * FROM books b WHERE isAvailable=1;");
            while (rs.next()) {
                int id = rs.getInt("book_id");
                String name = rs.getString("bookName");
                String sec = rs.getString("bookSec");
                int isA = rs.getInt("isAvailable");
                
    
               x+= String.format("%5d   %12s   %30s %5d \n",id,name,sec,isA );
                
    
            }
            dos.writeUTF( x);
        } catch (IOException e) {
            // TODO: handle exception
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            if(rs != null){
                 try{
                      rs.close();
                 } catch(Exception e){
                     e.printStackTrace();
                 }
                }
            }
    }
    /////Return books and set isAvailable=1//////////
    private void Returnbooks(int id) {
        PreparedStatement ps=null;
        try {
            ps = conn.prepareStatement("UPDATE books SET `isAvailable` = ? WHERE `book_id` = ?; ");
           
            try {
               // set the corresponding param
                ps.setInt(1, 1);
                ps.setInt(2,id );
                 // execute the delete statement
                ps.executeUpdate();
                
                System.out.println("UPDATED");
           
            } catch (Exception a) {
                a.printStackTrace();
            } finally {
               
                }
                if(ps != null){
                    try{
                        ps.close();
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
           
            
    
           
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }/////Borrow books and set isAvailable=0//////////
    private void BorrowBook(int id) {
        PreparedStatement ps=null;
        try {
            ps = conn.prepareStatement("UPDATE books SET `isAvailable` = ? WHERE `book_id` = ?; ");
           
            try {
               // set the corresponding param
                ps.setInt(1, 0);
                ps.setInt(2,id );
                 // execute the delete statement
                ps.executeUpdate();
                
                System.out.println("UPDATED");
           
            } catch (Exception a) {
                a.printStackTrace();
            } finally {
               
                }
                if(ps != null){
                    try{
                        ps.close();
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
           
            
    
           
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    ///to remove Book by id///////////
    private void removeBook(int id) {
        PreparedStatement ps=null;
        try {
            ps = conn.prepareStatement("DELETE FROM `books` WHERE `book_id`=? ;");
           
           
            try {
               // set the corresponding param
                ps.setInt(1,id );
                 // execute the delete statement
                ps.executeUpdate();
                
                System.out.println("removed");
           
            } catch (Exception a) {
                a.printStackTrace();
            } finally {
               
                }
                if(ps != null){
                    try{
                        ps.close();
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
           
            
    
           
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    ///to saerch By BookID/////////////////
    private void saerchByBookID(int id) {
        String x="";
        PreparedStatement ps=null;
        
        try {
            ps =  conn.prepareStatement("SELECT * FROM `books` b where `book_id` = ? ;");
            ps.setInt(1,id );
                System.out.println("found");
                if (ps.execute()) {
                    ResultSet rs = ps.getResultSet();
                    while (rs.next()) {
                        int id_b = rs.getInt("book_id");
                       String b_name = rs.getString("bookName");
                       String b_sec=rs.getString("bookSec");
                       int b_isA = rs.getInt("isAvailable");
                       
    
                        x+= String.format("%5d   %12s   %30s %5d \n",id_b,b_name,b_sec,b_isA );
                       
                    }
                }else {
                    System.out.println("\nNothing found!\n");
                }
    
                
    
                dos.writeUTF( x);
            }catch (IOException e) {
                // TODO: handle exception
            }
            catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            finally {
                if(ps != null){
                     try{
                          ps.close();
                     } catch(Exception e){
                         e.printStackTrace();
                     }
                    }
                }
    }
    ////to list all books//////////
    private void listbooks() {

        String x="";
        Statement st=null;
        ResultSet rs=null;
        try {
            st=  conn.createStatement();
            rs =  st.executeQuery("SELECT * FROM books b ;");
            while (rs.next()) {
                int id = rs.getInt("book_id");
                String name = rs.getString("bookName");
                String sec = rs.getString("bookSec");
                int isA = rs.getInt("isAvailable");
                
    
               x+= String.format("%5d   %12s   %30s %5d \n",id,name,sec,isA );
                
    
            }
            dos.writeUTF( x);
        } catch (IOException e) {
            // TODO: handle exception
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            if(rs != null){
                 try{
                      rs.close();
                 } catch(Exception e){
                     e.printStackTrace();
                 }
                }
            }
      
    }
    /////to add new book////////
    private void Insertbooks() {

        
        PreparedStatement ps=null;
        try {
            ps = conn.prepareStatement("INSERT INTO `books` (`book_id`, `bookName`, `bookSec`, `isAvailable`) VALUES(?, ?, ?, ?);");
       
           
            try {
                String bookName =dis.readUTF();
                String bookSec =dis.readUTF();
                int isAvailable =1;
                
int new_id=generatBookID();

//isAvailable by defulte =1

                System.out.println(new_id+"   "+bookName+"   "+bookSec+"   "+isAvailable+"   ");
                ps.setInt(1,new_id );
                ps.setString(2, bookName);
                ps.setString(3, bookSec);
                ps.setInt(4,isAvailable );
                int n = ps.executeUpdate();
                System.out.println(n);
                System.out.println("added");
           
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }  catch (Exception a) {
                a.printStackTrace();
            } finally {
               
                }
                if(ps != null){
                    try{
                        ps.close();
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
           
            
    
           
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
    }
    /////// to generat ID for book//////
    private int generatBookID() {
        int the_new_id=-1;
        Statement st;
        ResultSet rs=null;
        try {
            st = conn.createStatement();

         rs = st.executeQuery("SELECT MAX(book_id) as 'new_bookid' FROM  books ;");
        rs.next();

        the_new_id= rs.getInt("new_bookid") + 1;

       
        } catch (SQLException e) {
            // TODO: handle exception
        }
            finally {
    if(rs != null){
         try{
              rs.close();
         } catch(Exception e){
             e.printStackTrace();
         }
        }
    }
        return the_new_id;
    }
    /////saerch By ID to found Worker
    private void saerchByIDWorker(int id) {
        String x="";
        PreparedStatement ps=null;
        
        try {
            ps =  conn.prepareStatement("SELECT * FROM `users` u where `userID` = ? ;");
            ps.setInt(1,id );
                System.out.println("found");
                if (ps.execute()) {
                    ResultSet rs = ps.getResultSet();
                    while (rs.next()) {
                        int id_u = rs.getInt("userID");
                       String  username = rs.getString("username");
                       String password=rs.getString("password");
                       String role=rs.getString("role");
                       String fullname = rs.getString("fullName");
                       
    
                        x+= String.format("%5d   %12s   %30s %20s %50s \n",id_u,username,password,role,fullname );
                       
                    }
                }else {
                    System.out.println("\nNothing found!\n");
                }
    
                
    
                dos.writeUTF( x);
            }catch (IOException e) {
                // TODO: handle exception
            }
            catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            finally {
                if(ps != null){
                     try{
                          ps.close();
                     } catch(Exception e){
                         e.printStackTrace();
                     }
                    }
                }
        }
         
        ////to remove worker //////////
    private void removeWorker(int id) {
        Scanner keyboards = new Scanner(System.in);
       
        
      
        PreparedStatement ps=null;
        try {
            ps = conn.prepareStatement("DELETE FROM `users` WHERE `userID`=? ;");
            ps.setInt(1,id );
           
            try {
               // set the corresponding param
                ps.setInt(1,id );
                 // execute the delete statement
                ps.executeUpdate();
                
                System.out.println("removed");
           
            } catch (Exception a) {
                a.printStackTrace();
            } finally {
               
                }
                if(ps != null){
                    try{
                        ps.close();
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
           
            
    
           
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
    }
    //////to list all worker////////
    private void listWorker() {
        String x="";
        Statement st=null;
        ResultSet rs=null;
        try {
            st=  conn.createStatement();
            rs =  st.executeQuery("SELECT * FROM users u ;");
            while (rs.next()) {
                int id = rs.getInt("userID");
                String name = rs.getString("username");
                String pass = rs.getString("password");
                String role = rs.getString("role");
                String fulln = rs.getString("fullName");
    
               x+= String.format("%5d   %12s   %30s %20s %50s \n",id,name,pass,role,fulln );
                
    
            }
            dos.writeUTF( x);
        } catch (IOException e) {
            // TODO: handle exception
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            if(rs != null){
                 try{
                      rs.close();
                 } catch(Exception e){
                     e.printStackTrace();
                 }
                }
            }
      
    }


    // ADD New user with new ID////
     private static synchronized void InsertWorker( DataInputStream dis)  {
        Scanner keyboards = new Scanner(System.in);
       
        
      
        PreparedStatement ps=null;
        try {
            ps = conn.prepareStatement("INSERT INTO `users` (`userID`, `username`, `password`, `role`, `fullName`) VALUES(?, ?, ?, ?, ?);");
       
           
            try {
                String username =dis.readUTF();
                String password =dis.readUTF();
                String role =dis.readUTF();
                String fullname =dis.readUTF();
int new_id=generateuserID();
                System.out.println(new_id+"   "+username+"   "+password+"   "+role+"   "+fullname+"   ");
                ps.setInt(1,new_id );
                ps.setString(2, username);
                ps.setString(3,password );
                ps.setString(4, role);
                ps.setString(5, fullname);
                int n = ps.executeUpdate();
                System.out.println(n);
                System.out.println("added");
           
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }  catch (Exception a) {
                a.printStackTrace();
            } finally {
               
                }
                if(ps != null){
                    try{
                        ps.close();
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
           
            
    
           
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
    }
    ////generate user ID///////////
    private static int generateuserID() {
       
        int the_new_id=-1;
        Statement st;
        ResultSet rs=null;
        try {
            st = conn.createStatement();

         rs = st.executeQuery("SELECT MAX(userID) as 'new_userID' FROM users ;");
        rs.next();

        the_new_id= rs.getInt("new_userID") + 1;

       
        } catch (SQLException e) {
            // TODO: handle exception
        }
            finally {
    if(rs != null){
         try{
              rs.close();
         } catch(Exception e){
             e.printStackTrace();
         }
        }
    }


        
        return the_new_id;
        
    }
}

    


