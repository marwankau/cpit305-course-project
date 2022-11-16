package Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Patient {

    private Connection conn;
    private DataOutputStream dos;

    public Patient(Connection conn, DataOutputStream dos) {

        this.conn = conn;
        this.dos = dos;
    }
//dispaly all Patients
    public void SelectAllPatient(DataOutputStream dos) {
        try {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Patient");
            ResultSet rs = ps.executeQuery();

            String listOfPatient = String.format(
                    "========================================================\n%-5s %-30s %-30s\n",
                    "ID", "Patient name", "Patient Date of birth");
            while (rs.next()) {

                listOfPatient += String.format("%-5d %-30s %-30s \n",
                        rs.getInt("Patient_id"), rs.getString("patient_name"), rs.getString("patient_dob"));

            }
            dos.writeUTF(listOfPatient);
        } catch (Exception e) {

        }
    }

    // insert patient
    public void insertPatient(DataOutputStream dos, String patient_name, String patient_dob) throws IOException {
        try {
            PreparedStatement ps = conn
                    .prepareStatement("INSERT INTO Patient (patient_name, patient_dob) VALUES (?, ?);");
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
//if Patient has appointment or not 
    public boolean isPatientBooked(int id) {
        try {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM BookAppointment WHERE patient_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            String date = rs.getString("appointment_date");

            if (date != null) {
                return true;
            }

        } catch (Exception e) {

        }
        return false;
    }
//select patient by ID
    public boolean selectPatientByID(DataOutputStream dos, int id) {
        try {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Patient WHERE patient_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            String name = rs.getString("patient_name");
            String listOfPatient = "===================================================\n";
            if (name != null) {
                return true;
            }
            dos.writeUTF(listOfPatient);
        } catch (Exception e) {

        }
        return false;
    }

    //to check if there is Patient or not
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
//update patient
    public void updatePatient(DataOutputStream dos, String patient_name, String patient_dob, int Patient_id)
            throws IOException {
        try {
            PreparedStatement ps = conn
                    .prepareStatement("UPDATE Patient SET patient_name = ?, patient_dob = ?  WHERE patient_id = ?");
            ps.setString(1, patient_name);
            ps.setString(2, patient_dob);
            ps.setInt(3, Patient_id);
            boolean rs = ps.execute();
            dos.writeUTF("Patient has been successfully updated");
        } catch (Exception e) {

        }
    }
}