package Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Appointment {
  private Connection conn;
  private DataOutputStream dos;

  public Appointment(Connection conn, DataOutputStream dos) {

    this.conn = conn;
    this.dos = dos;
  }

  // display all appointments
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
}
