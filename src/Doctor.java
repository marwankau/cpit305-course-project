import java.util.Date;


public class Doctor {
    private String doctorName;
    private int doctorId;
    private String Doctorspeciality;
   

    public Doctor(String doctorName, int doctorId, String Doctorspeciality) {
        this.doctorName = doctorName;
        this.doctorId = doctorId;
        this.Doctorspeciality = Doctorspeciality;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorspeciality() {
        return Doctorspeciality;
    }

    public void setDoctorspeciality(String Doctorspeciality) {
        this.Doctorspeciality = Doctorspeciality;
    }

   
    
}
