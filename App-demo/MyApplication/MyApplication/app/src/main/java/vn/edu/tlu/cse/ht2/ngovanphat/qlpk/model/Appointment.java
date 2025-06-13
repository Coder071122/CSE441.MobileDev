package vn.edu.tlu.cse.ht2.ngovanphat.qlpk.model;

public class Appointment {
    private String id;
    private String date;
    private String time;
    private String doctorEmail;
    private String patientEmail;
    private String status;
    private String notes;

    public Appointment() {
    }

    public Appointment(String id, String date, String time, String doctorEmail, String patientEmail, String status, String notes) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.doctorEmail = doctorEmail;
        this.patientEmail = patientEmail;
        this.status = status;
        this.notes = notes;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public String getStatus() {
        return status;
    }

    public String getNotes() {
        return notes;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
