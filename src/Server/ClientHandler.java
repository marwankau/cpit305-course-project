package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.net.Socket;
import java.sql.Connection;
import java.sql.Date;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;

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
      Doctor doctor = new Doctor(conn, dos);
      Appointment appointment = new Appointment(conn, dos);

      String line;
      int choiceMenu;
      while (true) {

        displayMenu(dos);

        line = dis.readUTF();

        try {
          choiceMenu = Integer.parseInt(line);
        } catch (NumberFormatException e) {
          dos.writeUTF("Enter a valid choice!");
          continue;
        }
        // doctor option menu

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

            doctor.getAllDoctors(dos);

          } else if (choice == 2) {
            dos.writeUTF("Enter doctor name: ");
            String docName = dis.readUTF();
            dos.writeUTF("Enter doctor speciality: ");
            String docSpeciality = dis.readUTF();
            doctor.insertDoctor(dos, docName, docSpeciality);
          } else if (choice == 3) {
            doctor.getAllDoctors(dos);
            dos.writeUTF("Enter doctor ID to be deleted: ");
            int id = Integer.parseInt(dis.readUTF());
            boolean validID = doctor.selectDoctorByID(dos, id);
            if (!validID) {
              dos.writeUTF("Enter a valid Doctor ID!");
              continue;
            }
            doctor.deleteDoctor(dos, id);
          } else if (choice == 4) {
            doctor.getAllDoctors(dos);
            dos.writeUTF("Enter doctor ID to be updated: ");
            try {
              int id = Integer.parseInt(dis.readUTF());
              boolean validID = doctor.selectDoctorByID(dos, id);
              if (!validID) {
                dos.writeUTF("Enter a valid Doctor ID!");
                continue;
              }
              dos.writeUTF("Enter Doctor name: ");
              String doctor_name = dis.readUTF();
              dos.writeUTF("Enter Doctor speciality: ");
              String doctor_speciality = dis.readUTF();

              doctor.updateDoctor(dos, doctor_name, doctor_speciality, id);
            } catch (NumberFormatException e) {
              dos.writeUTF("Enter a valid ID!");
              continue;
            }
          }
          // patients option menu
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
            boolean validID = patient.selectPatientByID(dos, id);
            if (!validID) {
              dos.writeUTF("Enter a valid patient ID!");
              continue;
            }
            patient.deletePatient(dos, id);
          } else if (choice == 4) {
            patient.SelectAllPatient(dos);
            dos.writeUTF("Enter patient id: ");
            try {
              int id = Integer.parseInt(dis.readUTF());
              boolean validID = patient.selectPatientByID(dos, id);
              if (!validID) {
                dos.writeUTF("Enter a valid patient ID!");
                continue;
              }
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

          if (doctor.getTotalDoctors() < 1) {
            dos.writeUTF("Please insert doctors to register appointments!");
            continue;
          }

          if (patient.getTotalPatient() < 1) {
            dos.writeUTF("Please insert Patients to register appointments!");
            continue;
          }
          try {
            doctor.getAllDoctors(dos);
            dos.writeUTF("Choose a doctor by id: ");

            int docID = Integer.parseInt(dis.readUTF());
            // Check if doctor ID is valid
            boolean validID = doctor.selectDoctorByID(dos, docID);

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
            Date day;
            // To save formatted date
            String formatedDate;
            // Get current date
            LocalDateTime now = LocalDateTime.now();
            // To format date
            SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
            if (appointmenDates.size() < 1) {
              for (int i = 0; i < 7; i++) {
                // This is used to provide a list from the current date until the next 7 days
                day = new Date(now.getYear() - 1900, now.getMonthValue() - 1, now.getDayOfMonth() + i);
                formatedDate = formatter.format(day);

                appointmenDates.add(formatedDate);
              }
            }
            // to save all dates in one variable
            String dates = "";
            dos.writeUTF("Choose one of the following dates");
            // Display dates alongside numbers
            for (int i = 0; i < appointmenDates.size(); i++) {
              dates += i + 1 + ". " + appointmenDates.get(i) + "\n";
            }
            dos.writeUTF(dates);

            dos.writeUTF("Enter your choice: ");
            line = dis.readUTF();

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

              String appointmentDate = appointmenDates.get(choice - 1) + " " + time + " PM";
              // Method to check if the doctor has an appointment in the same chosen date and
              // time
              boolean takenDate = appointment.checkAppointmentDate(appointmentDate, docID);
              if (takenDate) {
                dos.writeUTF("This Date and time is taken, Choose another date");
                continue;
              }
              appointment.insertAppointment(dos, appointmentDate, docID, patientID);
            } else {
              dos.writeUTF("Enter a valid choice");
            }
          } catch (Exception e) {
            dos.writeUTF("Not a valid choice");
            continue;
          }
        } else if (choiceMenu == 4) {
          appointment.getAllAppointments(dos);
          dos.writeUTF("Choose appointment ID to be deleted: ");
          int id = Integer.parseInt(dis.readUTF());
          appointment.deleteAppointment(dos, id);
        } else if (choiceMenu == 5) {
          appointment.getAllAppointments(dos);
        } else {
          dos.writeUTF("Enter a valid choice!");
        }
      }

    } catch (NoSuchElementException | EOFException e) {
      System.out.println("Client disconnected");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  // wlocome menu
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

}
