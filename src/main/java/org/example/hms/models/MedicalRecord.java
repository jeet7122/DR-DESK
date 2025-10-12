package org.example.hms.models;

import java.time.LocalDate;

public class MedicalRecord {
    private int id;
    private int patientId;
    private String condition;
    private String description;
    private LocalDate dateDiagnosed;
    private String currentStatus;

    public MedicalRecord(String currentStatus, LocalDate dateDiagnosed, String description, String condition, int patientId, int id) {
        this.currentStatus = currentStatus;
        this.dateDiagnosed = dateDiagnosed;
        this.description = description;
        this.condition = condition;
        this.patientId = patientId;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateDiagnosed() {
        return dateDiagnosed;
    }

    public void setDateDiagnosed(LocalDate dateDiagnosed) {
        this.dateDiagnosed = dateDiagnosed;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }
}
