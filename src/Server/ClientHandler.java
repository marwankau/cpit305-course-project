package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;



public class ClientHandler extends Thread{
  Socket socket;
  static Connection conn; 

  
  public ClientHandler(Socket socket, Connection conn) {
    this.socket = socket;
    this.conn = conn;
  }
  
  @Override
  public void run() {
    
    try {
      InputStream in = socket.getInputStream();
      OutputStream out = socket.getOutputStream();

      DataOutputStream dos = new DataOutputStream(out);
      DataInputStream dis = new DataInputStream(in);

      String line;
      while(true){
      
      displayMenu(dos);


       line = dis.readUTF();
       System.out.println(line);
      if(Integer.parseInt(line) == 1){
        dos.writeUTF("---------Manage Doctors---------");
        dos.writeUTF("1. Display all doctors");
        dos.writeUTF("2. Add doctor");
        dos.writeUTF("3. Delete doctor");
        dos.writeUTF("4. Update doctor");
        dos.writeUTF("Enter your choice: ");
        line = dis.readUTF();
        int choice = Integer.parseInt(line);
        if(choice == 1){
          getAllDoctors(dos);
        }else if(choice == 2){
          dos.writeUTF("Enter doctor name: ");
          String docName = dis.readUTF();
          dos.writeUTF("Enter doctor speciality: ");
          String docSpeciality = dis.readUTF();
          insertDoctor(dos, docName, docSpeciality);
        }else if(choice == 3){
          getAllDoctors(dos);
          dos.writeUTF("Enter doctor ID to be deleted: ");
          int id = Integer.parseInt(dis.readUTF());
          deleteDoctor(dos, id);
        }
      }else if(Integer.parseInt(line) == 2){
        dos.writeUTF("add a doctor");
      }
    }
       
    } catch (Exception e) {
      // TODO: handle exception
    }
  }

  public void displayMenu(DataOutputStream dos) throws IOException{
    dos.writeUTF("---------Welcome to Hospital Reservation System---------");
    dos.writeUTF("1. Manage Doctors.");
    dos.writeUTF("2. Manage Patients.");
    dos.writeUTF("3. Book Appointment.");
    dos.writeUTF("4. Cancel Appointment.");
    dos.writeUTF("10. Exit.");
    dos.writeUTF("Enter your choice: ");
    
  }


  public void getAllDoctors(DataOutputStream dos){
    try {
     
      PreparedStatement ps = conn.prepareStatement("SELECT * FROM Doctor");
      ResultSet rs = ps.executeQuery();
      dos.writeUTF("Doc#\t\tDoctor name\t\tDoctor Speciality");
      dos.writeUTF("===================================================");
      while(rs.next()){
        dos.writeUTF(rs.getString("doctor_id") + "\t\t" + rs.getString("doctor_name") + "\t\t" + rs.getString("doctor_speciality"));
      }
    } catch (Exception e) {
      // TODO: handle exception
    }
  }

  public void insertDoctor(DataOutputStream dos, String doctor_name, String doctor_speciality) throws IOException{
    try {
      PreparedStatement ps = conn.prepareStatement("INSERT INTO Doctor (doctor_name, doctor_speciality) VALUES (?, ?);");
      ps.setString(1, doctor_name);
      ps.setString(2, doctor_speciality);
      boolean rs = ps.execute();
       dos.writeUTF("Successfully inserted");
      

    } catch (Exception e) {
      dos.writeUTF("Inserting failed");
    }
  }

  public void deleteDoctor(DataOutputStream dos, int doctor_id) throws IOException{
    try {
      PreparedStatement ps = conn.prepareStatement("DELETE FROM Doctor WHERE doctor_id = ?");
      ps.setInt(1, doctor_id);
      boolean rs = ps.execute();
       dos.writeUTF("Successfully deleted");
      

    } catch (Exception e) {
      dos.writeUTF("deleting failed");
    }
  }

}
