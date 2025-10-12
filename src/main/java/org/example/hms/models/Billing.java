package org.example.hms.models;

import java.time.LocalDateTime;

public class Billing {
    private int billId;
    private int appointmentId;   // FK → Appointment
    private int patientId;       // FK → Patient
    private double consultationFee;
    private double medicineFee;
    private double serviceFee;
    private double totalAmount;
    private LocalDateTime billDate;
    private boolean isPaid;

    @Override
    public String toString() {
        return "Billing{" +
                "billId=" + billId +
                ", appointmentId=" + appointmentId +
                ", patientId=" + patientId +
                ", consultationFee=" + consultationFee +
                ", medicineFee=" + medicineFee +
                ", serviceFee=" + serviceFee +
                ", totalAmount=" + totalAmount +
                ", billDate=" + billDate +
                ", isPaid=" + isPaid +
                '}';
    }

    public Billing() {
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }

    public double getMedicineFee() {
        return medicineFee;
    }

    public void setMedicineFee(double medicineFee) {
        this.medicineFee = medicineFee;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDateTime billDate) {
        this.billDate = billDate;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public Billing(int billId, int appointmentId, int patientId, double consultationFee, double medicineFee, double serviceFee, double totalAmount, LocalDateTime billDate, boolean isPaid) {
        this.billId = billId;
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.consultationFee = consultationFee;
        this.medicineFee = medicineFee;
        this.serviceFee = serviceFee;
        this.totalAmount = totalAmount;
        this.billDate = billDate;
        this.isPaid = isPaid;
    }
}
