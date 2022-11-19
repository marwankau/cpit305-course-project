package Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Doctor {

  private Connection conn;
  private DataOutputStream dos;

  public Doctor(Connection conn, DataOutputStream dos) {

    this.conn = conn;
    this.dos = dos;
  }

  // display play all doctors
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
      System.out.println("Error occured while getting Doctors");
    }

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
      dos.writeUTF("Doctor has been successfully deleted");

    } catch (Exception e) {
      dos.writeUTF("deleting failed");
    }
  }

  // update doctor info
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

  // select doctor by ID
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

  // to check if there is available doctors to book
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
}
