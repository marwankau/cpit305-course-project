package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class database {
    Connection conn;
  
  public database(Connection conn){
    this.conn = conn;
  }
   
  public String displayMenu(){
   String menu = "---------Welcome to Hospital Reservation System---------\n" + 
    "1. Add Doctor.\n"+
     "2. Add Patient.\n"+
     "3. Book Appointment.\n"+
     "4. Cancel Appointment.\n"+
     "10. Exit.\n" + 
     "Enter your choice: ";
     return menu;
  }
  public String getAllDoctors() throws SQLException{
    PreparedStatement ps = conn.prepareStatement("SELECT * FROM Doctor");
    ResultSet rs = ps.executeQuery();
    
    return "Sample";
  }

}
