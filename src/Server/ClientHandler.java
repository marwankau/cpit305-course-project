package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientHandler extends Thread {
  Socket socket;
  static Connection conn;
  ArrayList<String> appointmenDates = new ArrayList<>();

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
      int choiceMenu;
      while (true) {

        displayMenu(dos);

        line = dis.readUTF();
        System.out.println(line);
        try {
          choiceMenu = Integer.parseInt(line);
        } catch (NumberFormatException e) {
          dos.writeUTF("Enter a valid choice!");
          continue;
        }

        if (choiceMenu == 1) {
          dos.writeUTF("---------Manage Doctors---------");
          dos.writeUTF("1. Display all doctors");
          dos.writeUTF("2. Add doctor");
          dos.writeUTF("3. Delete doctor");
          dos.writeUTF("4. Update doctor");
          dos.writeUTF("Enter your choice: ");
          line = dis.readUTF();
          int choice;
          try {
            choice = Integer.parseInt(line);
          } catch (NumberFormatException e) {
            dos.writeUTF("Enter a valid choice!");
            continue;
          }
          if (choice == 1) {
            getAllDoctors(dos);

          } else if (choice == 2) {
            dos.writeUTF("Enter doctor name: ");
            String docName = dis.readUTF();
            dos.writeUTF("Enter doctor speciality: ");
            String docSpeciality = dis.readUTF();
            insertDoctor(dos, docName, docSpeciality);
          } else if (choice == 3) {
            getAllDoctors(dos);
            dos.writeUTF("Enter doctor ID to be deleted: ");
            int id = Integer.parseInt(dis.readUTF());
            deleteDoctor(dos, id);
          }
        } else if (choiceMenu == 2) {
          dos.writeUTF("---------Manage Patients---------");
          dos.writeUTF("1. Display all patients");
          dos.writeUTF("2. Add patients");
          dos.writeUTF("3. Delete patients");
          dos.writeUTF("4. Update patients");
          dos.writeUTF("Enter your choice: ");
          line = dis.readUTF();
          int choice;
          try {
            choice = Integer.parseInt(line);
          } catch (NumberFormatException e) {
            dos.writeUTF("Enter a valid choice!");
            continue;
          }

          if (choice == 1) {
            SelectAllPatient(dos);
          } else if (choice == 2) {
            dos.writeUTF("Enter patient name: ");
            String patient_name = dis.readUTF();
            dos.writeUTF("Enter date of birth with the following format (YYY-MM-DD): ");
            String patient_dob = dis.readUTF();
            insertPatient(dos, patient_name, patient_dob);
          } else if (choice == 3) {
            SelectAllPatient(dos);
            dos.writeUTF("Enter patient ID to be deleted: ");
            int id = Integer.parseInt(dis.readUTF());
            deletePatient(dos, id);
          } else if (choice == 4) {
            SelectAllPatient(dos);
            dos.writeUTF("Enter patient id: ");
            try {
              int id = Integer.parseInt(dis.readUTF());

              dos.writeUTF("Enter patient name: ");
              String patient_name = dis.readUTF();
              dos.writeUTF("Enter date of birth with the following format (YYYY-MM-DD): ");
              String patient_dob = dis.readUTF();
              updatePatient(dos, patient_name, patient_dob, id);
            } catch (Exception e) {
              dos.writeUTF("Enter a valid ID!");
              continue;
            }
          }
        } else if (choiceMenu == 3) {
          Date day;
          String formatedDate;
          LocalDateTime now = LocalDateTime.now();

          dos.writeUTF("---------Book Appointment---------");
          if (getTotalDoctors() < 1) {
            dos.writeUTF("Please insert doctors to register appointments!");
            continue;
          }

          if (getTotalPatient() < 1) {
            dos.writeUTF("Please insert Patients to register appointments!");
            continue;
          }
          try {

            SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
            if (appointmenDates.size() < 1) {
              for (int i = 0; i < 7; i++) {
                // This is used to provide a list from the current date until the next 7 days
                day = new Date(now.getYear() - 1900, now.getMonthValue() - 1, now.getDayOfMonth() + i);
                formatedDate = formatter.format(day);
                System.out.println(formatedDate);
                appointmenDates.add(formatedDate);
              }
            }
            dos.writeUTF("Choose one of the following dates");
            for (int i = 0; i < appointmenDates.size(); i++) {
              dos.writeUTF(i + 1 + ". " + appointmenDates.get(i));
            }

            dos.writeUTF("Enter your choice: ");
            line = dis.readUTF();
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
          int choice = Integer.parseInt(line);
          if (choice > 1 || choice <= 7) {
            dos.writeUTF("Choose a time From 1 PM to 5 PM");
            for (int i = 1; i < 6; i++) {
              dos.writeUTF(i + ". " + i + " PM");
            }
            dos.writeUTF("Enter your choice: ");
            line = dis.readUTF();
            choice = Integer.parseInt(line);
          } else {
            dos.writeUTF("Enter a valid choice");
          }
        } else {
          dos.writeUTF("Enter a valid choice!");
        }
      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void displayMenu(DataOutputStream dos) throws IOException {
    dos.writeUTF("---------Welcome to Hospital Reservation System---------");
    dos.writeUTF("1. Manage Doctors.");
    dos.writeUTF("2. Manage Patients.");
    dos.writeUTF("3. Book Appointment.");
    dos.writeUTF("4. Cancel Appointment.");
    dos.writeUTF("10. Exit.");
    dos.writeUTF("Enter your choice: ");

  }

  public void getAllDoctors(DataOutputStream dos) {
    try {

      PreparedStatement ps = conn.prepareStatement("SELECT * FROM Doctor");
      ResultSet rs = ps.executeQuery();
      dos.writeUTF("Doc#\t\tDoctor name\t\tDoctor Speciality");
      dos.writeUTF("===================================================");
      while (rs.next()) {
        dos.writeUTF(rs.getString("doctor_id") + "\t\t" + rs.getString("doctor_name") + "\t\t"
            + rs.getString("doctor_speciality"));
      }
    } catch (Exception e) {
      // TODO: handle exception
    }
  }

  public void SelectAllPatient(DataOutputStream dos) {
    try {

      PreparedStatement ps = conn.prepareStatement("SELECT * FROM Patient");
      ResultSet rs = ps.executeQuery();
      dos.writeUTF("Patient#\t\tPatient_name\t\tpatient_dob");
      dos.writeUTF("===================================================");
      while (rs.next()) {
        dos.writeUTF(
            rs.getInt("Patient_id") + "\t\t" + rs.getString("patient_name") + "\t\t" + rs.getString("patient_dob"));
      }
    } catch (Exception e) {

    }
  }

  public int getTotalDoctors() {
    try {

      Statement stmt = conn.createStatement();
      // Retrieving the data
      ResultSet rs = stmt.executeQuery("select count(*) from Doctor");
      rs.next();
      // Moving the cursor to the last row
      return rs.getInt("count(*)");
    } catch (Exception e) {
      // TODO: handle exception
    }
    return 0;
  }

  public int getTotalPatient() {
    try {

      Statement stmt = conn.createStatement();
      // Retrieving the data
      ResultSet rs = stmt.executeQuery("select count(*) from Patient");
      rs.next();
      // Moving the cursor to the last row
      return rs.getInt("count(*)");
    } catch (Exception e) {
      // TODO: handle exception
    }
    return 0;
  }

  // insert doctor
  public void insertDoctor(DataOutputStream dos, String doctor_name, String doctor_speciality) throws IOException {
    try {
      PreparedStatement ps = conn
          .prepareStatement("INSERT INTO Doctor (doctor_name, doctor_speciality) VALUES (?, ?);");
      ps.setString(1, doctor_name);
      ps.setString(2, doctor_speciality);
      boolean rs = ps.execute();
      dos.writeUTF("Successfully inserted");

    } catch (Exception e) {
      dos.writeUTF("Inserting failed");
    }
  }

  // delete doctor
  public void deleteDoctor(DataOutputStream dos, int doctor_id) throws IOException {
    try {
      PreparedStatement ps = conn.prepareStatement("DELETE FROM Doctor WHERE doctor_id = ?");
      ps.setInt(1, doctor_id);
      boolean rs = ps.execute();
      dos.writeUTF("Successfully deleted");

    } catch (Exception e) {
      dos.writeUTF("deleting failed");
    }
  }

  // insert patient
  public void insertPatient(DataOutputStream dos, String patient_name, String patient_dob) throws IOException {
    try {
      PreparedStatement ps = conn.prepareStatement("INSERT INTO Patient (patient_name, patient_dob) VALUES (?, ?);");
      ps.setString(1, patient_name);
      ps.setString(2, patient_dob);
      boolean rs = ps.execute();
      dos.writeUTF("Patient has been successfully inserted");
    } catch (Exception e) {
      dos.writeUTF("Inserting has been failed");
    }
  }

  // delelet Patients
  public void deletePatient(DataOutputStream dos, int Patient_id) throws IOException {
    try {
      PreparedStatement ps = conn.prepareStatement("DELETE FROM Patient WHERE Patient_id = ?");
      ps.setInt(1, Patient_id);
      boolean rs = ps.execute();
      dos.writeUTF("Patient has been successfully deleted");

    } catch (Exception e) {
      dos.writeUTF("delete has been failed");
    }
  }

  public void updateDoctor(DataOutputStream dos, String doctor_speciality, int doctor_id) throws IOException {
    try {
      PreparedStatement ps = conn.prepareStatement("UPDATE FROM Doctor SET doctor_speciality= ?  WHERE doctor_id = ?");
      ps.setString(1, doctor_speciality);
      ps.setInt(2, doctor_id);
      boolean rs = ps.execute();
      dos.writeUTF("Doctor has been successfully updated");

    } catch (Exception e) {
      dos.writeUTF("update has been failed");
    }
  }

  public void updatePatient(DataOutputStream dos, String patient_name, String patient_dob, int Patient_id)
      throws IOException {
    try {
      PreparedStatement ps = conn
          .prepareStatement("UPDATE FROM Patient SET patient_name= ? , patient_dob= ?   WHERE Patient_id = ?");
      ps.setString(1, patient_name);
      ps.setString(2, patient_dob);
      ps.setInt(3, Patient_id);
      boolean rs = ps.execute();
      dos.writeUTF("Patient has been successfully updated");
    } catch (Exception e) {

    }
  }
}
