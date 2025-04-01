package com.medicalapp;

import java.sql.Date;

public class Appointment {
    private int id;
    private int patientId;
    private Date appointmentDate;
    private String purpose;
    
    // Constructor
    public Appointment() {
    }
    
    public Appointment(int id, int patientId, Date appointmentDate, String purpose) {
        this.id = id;
        this.patientId = patientId;
        this.appointmentDate = appointmentDate;
        this.purpose = purpose;
    }
    
    // Getters and setters
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
    
    public Date getAppointmentDate() {
        return appointmentDate;
    }
    
    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
    
    public String getPurpose() {
        return purpose;
    }
    
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
    
    // Additional methods for managing appointments
    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", appointmentDate=" + appointmentDate +
                ", purpose='" + purpose + '\'' +
                '}';
    }
}