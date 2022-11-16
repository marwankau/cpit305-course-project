package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
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
      Patient patient = new Patient(conn, dos);
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
          } else if (choice == 4) {
            getAllDoctors(dos);
            dos.writeUTF("Enter doctor ID to be updated: ");
            try {
              int id = Integer.parseInt(dis.readUTF());

              dos.writeUTF("Enter Doctor name: ");
              String doctor_name = dis.readUTF();
              dos.writeUTF("Enter Doctor speciality: ");
              String doctor_speciality = dis.readUTF();
              updateDoctor(dos, doctor_name, doctor_speciality, id);
            } catch (Exception e) {
              dos.writeUTF("Enter a valid ID!");
              continue;
            }
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
            patient.SelectAllPatient(dos);
          } else if (choice == 2) {
            dos.writeUTF("Enter patient name: ");
            String patient_name = dis.readUTF();
            dos.writeUTF("Enter date of birth with the following format (YYYY-MM-DD): ");
            String patient_dob = dis.readUTF();
            patient.insertPatient(dos, patient_name, patient_dob);
          } else if (choice == 3) {
            patient.SelectAllPatient(dos);
            dos.writeUTF("Enter patient ID to be deleted: ");
            int id = Integer.parseInt(dis.readUTF());
            patient.deletePatient(dos, id);
          } else if (choice == 4) {
            patient.SelectAllPatient(dos);
            dos.writeUTF("Enter patient id: ");
            try {
              int id = Integer.parseInt(dis.readUTF());

              dos.writeUTF("Enter patient name: ");
              String patient_name = dis.readUTF();
              dos.writeUTF("Enter date of birth with the following format (YYYY-MM-DD): ");
              String patient_dob = dis.readUTF();
              patient.updatePatient(dos, patient_name, patient_dob, id);
            } catch (Exception e) {
              dos.writeUTF("Enter a valid ID!");
              continue;
            }
          }
        } else if (choiceMenu == 3) {
          Date day;
          String formatedDate;
          LocalDateTime now = LocalDateTime.now();

          if (getTotalDoctors() < 1) {
            dos.writeUTF("Please insert doctors to register appointments!");
            continue;
          }

          if (patient.getTotalPatient() < 1) {
            dos.writeUTF("Please insert Patients to register appointments!");
            continue;
          }
          try {
            getAllDoctors(dos);
            dos.writeUTF("Choose a doctor by id: ");

            int docID = Integer.parseInt(dis.readUTF());
            // Check if doctor ID is valid
            boolean validID = selectDoctorByID(dos, docID);
            System.out.println(validID);
            if (!validID) {
              dos.writeUTF("Not a valid doctor ID");
              continue;
            }
            patient.SelectAllPatient(dos);
            dos.writeUTF("Choose a patient by id: ");
            int patientID = Integer.parseInt(dis.readUTF());
            // Check if patient ID is valid
            validID = patient.selectPatientByID(dos, patientID);
            if (!validID) {
              dos.writeUTF("Not a valid patient ID");
              continue;
            }

            // call method to check if patient has booked an appointment before or not
            boolean patientBooked = patient.isPatientBooked(patientID);
            if (patientBooked) {
              dos.writeUTF("Patient already has a booked appointment");
              continue;
            }

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
            String dates = "";
            dos.writeUTF("Choose one of the following dates");
            // Display dates alongside numbers
            for (int i = 0; i < appointmenDates.size(); i++) {
              dates += i + 1 + ". " + appointmenDates.get(i) + "\n";
            }
            dos.writeUTF(dates);

            dos.writeUTF("Enter your choice: ");

            int choosenDate = Integer.parseInt(line = dis.readUTF());

            int choice = Integer.parseInt(line);
            // Check if chosen date within range
            if (choice >= 1 && choice <= 7) {
              dos.writeUTF("Choose a time From 1 PM to 5 PM");
              // Generate time from 1 to 5 PM
              for (int i = 1; i < 6; i++) {
                dos.writeUTF(i + ". " + i + " PM");
              }
              dos.writeUTF("Enter your choice: ");
              line = dis.readUTF();
              int time = Integer.parseInt(line);
              if (time < 1 || time > 5) {
                dos.writeUTF("Not a valid time");
                continue;
              }

              String appointmentDate = appointmenDates.get(choosenDate - 1) + " " + time + " PM";
              // Method to check if the doctor has an appointment in the same chosen date and
              // time
              boolean takenDate = checkAppointmentDate(appointmentDate, docID);
              if (takenDate) {
                dos.writeUTF("This Date and time is taken, Choose another date");
                continue;
              }
              insertAppointment(dos, appointmentDate, docID, patientID);
            } else {
              dos.writeUTF("Enter a valid choice");
            }
          } catch (Exception e) {
            dos.writeUTF("Not a valid choice");
            continue;
          }
        } else if (choiceMenu == 4) {
          getAllAppointments(dos);
          dos.writeUTF("Choose appointment ID to be deleted: ");
          int id = Integer.parseInt(dis.readUTF());
          deleteAppointment(dos, id);
        } else if (choiceMenu == 5) {
          getAllAppointments(dos);
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
    dos.writeUTF("5. Display Appointments.");
    dos.writeUTF("0. Exit.");
    dos.writeUTF("Enter your choice: ");

  }

  public void getAllAppointments(DataOutputStream dos) throws SQLException {
    try {
      Statement st = conn.createStatement();
      String listOfDoctors = String.format(
          "========================================================\n%-10s %-10s %-10s %20s\n",
          "ID", "Doctor ID", "Patient ID", "Appointment Date");
      ResultSet rs = st.executeQuery("SELECT * FROM BookAppointment");
      while (rs.next()) {

        listOfDoctors += String.format("%-10d %-10s %-10s %20s \n",
            rs.getInt("appointment_id"), rs.getInt("doctor_id"), rs.getInt("patient_id"),
            rs.getString("appointment_date"));
      }
      dos.writeUTF(listOfDoctors);
    } catch (Exception e) {

    }

  }

  // insert doctor
  public synchronized void insertAppointment(DataOutputStream dos, String appointment_date, int doctor_id,
      int patient_id)
      throws IOException {
    try {
      PreparedStatement ps = conn
          .prepareStatement("INSERT INTO BookAppointment (doctor_id, patient_id, appointment_date) VALUES (?, ?, ?);");
      ps.setInt(1, doctor_id);
      ps.setInt(2, patient_id);
      ps.setString(3, appointment_date);
      boolean rs = ps.execute();
      dos.writeUTF("Choosen date: " + appointment_date + "\nSuccessfully inserted");

    } catch (Exception e) {
      dos.writeUTF("Inserting failed");
    }
  }

  // insert doctor
  public boolean checkAppointmentDate(String appointment_date, int doctor_id)
      throws IOException {
    try {
      PreparedStatement ps = conn
          .prepareStatement("SELECT * FROM BookAppointment WHERE appointment_date = ? AND doctor_id = ?");
      ps.setString(1, appointment_date);
      ps.setInt(2, doctor_id);

      ResultSet rs = ps.executeQuery();
      String date = rs.getString("appointment_date");

      if (date != null) {
        return true;
      }

    } catch (Exception e) {

    }
    return false;
  }

  public void getAllDoctors(DataOutputStream dos) throws SQLException {
    try {
      Statement st = conn.createStatement();
      String listOfDoctors = String.format(
          "========================================================\n%-5s %-30s %-30s\n",
          "ID", "Doctor name", "Doctor speciality");
      ResultSet rs = st.executeQuery("SELECT * FROM Doctor");
      while (rs.next()) {

        listOfDoctors += String.format("%-5d %-30s %-30s \n",
            rs.getInt("doctor_id"), rs.getString("doctor_name"), rs.getString("doctor_speciality"));
      }
      dos.writeUTF(listOfDoctors);
    } catch (Exception e) {

    }

  }

  public void selectAllAppointments(DataOutputStream dos) {
    try {

      PreparedStatement ps = conn.prepareStatement("SELECT * FROM BookAppointment");
      ResultSet rs = ps.executeQuery();

      String listOfAppointments = String.format(
          "========================================================\n%-5d %-10d %-10d %-30s\n",
          "ID", "Doctor ID", "Patient ID", "Appointment Date");
      while (rs.next()) {

        listOfAppointments += String.format("%-5s %-10s %-10s %-30s \n",
            rs.getInt("appointment_id"), rs.getInt("doctor_id"), rs.getInt("patient_id"),
            rs.getString("appointment_date"));

      }
      dos.writeUTF(listOfAppointments);
    } catch (Exception e) {

    }
  }

  public boolean selectDoctorByID(DataOutputStream dos, int id) {
    try {

      PreparedStatement ps = conn.prepareStatement("SELECT * FROM Doctor WHERE doctor_id = ?");
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      String name = rs.getString("doctor_name");

      if (name != null) {
        return true;
      }

    } catch (Exception e) {

    }
    return false;
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

  // delete appointment
  public void deleteAppointment(DataOutputStream dos, int appointment_id) throws IOException {
    try {
      PreparedStatement ps = conn.prepareStatement("DELETE FROM BookAppointment WHERE appointment_id = ?");
      ps.setInt(1, appointment_id);
      boolean rs = ps.execute();
      dos.writeUTF("Successfully deleted");

    } catch (Exception e) {
      dos.writeUTF("deleting failed");
    }
  }

  public void updateDoctor(DataOutputStream dos, String doctor_name, String doctor_speciality, int doctor_id)
      throws IOException {
    try {
      PreparedStatement ps = conn
          .prepareStatement("UPDATE Doctor SET doctor_name = ?, doctor_speciality= ?  WHERE doctor_id = ?");
      ps.setString(1, doctor_name);
      ps.setString(2, doctor_speciality);
      ps.setInt(3, doctor_id);
      boolean rs = ps.execute();
      dos.writeUTF("Doctor has been successfully updated");

    } catch (Exception e) {
      dos.writeUTF("update has been failed");
    }
  }

}
