package org.example.hms.dto;


import java.time.LocalDateTime;

public class AppointmentDTO {
    private int appointmentId;
    private int doctorId;
    private int patientId;
    private LocalDateTime appointmentDate;
    private String status;
    private String notes;

    public AppointmentDTO() {
    }

    @Override
    public String toString() {
        return "AppointmentDTO{" +
                "appointmentId=" + appointmentId +
                ", doctorId=" + doctorId +
                ", patientId=" + patientId +
                ", appointmentDate=" + appointmentDate +
                ", status='" + status + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
