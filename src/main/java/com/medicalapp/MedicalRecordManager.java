package com.medicalapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages CRUD operations for MedicalRecord objects in the database.
 */
public class MedicalRecordManager {
    private Connection dbConnection;

    /**
     * Constructor that accepts a database connection.
     * 
     * @param dbConnection The database connection to use
     */
    public MedicalRecordManager(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    /**
     * Adds a new medical record to the database.
     * 
     * @param record The MedicalRecord object to add
     * @return The ID of the newly inserted medical record
     */
    public int addMedicalRecord(MedicalRecord record) {
        String sql = "INSERT INTO medical_records (patient_id, visit_date, diagnosis, treatment_plan) " +
                     "VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement statement = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, record.getPatientId());
            statement.setDate(2, record.getVisitDate());
            statement.setString(3, record.getDiagnosis());
            statement.setString(4, record.getTreatmentPlan());
            
            int affectedRows = statement.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Creating medical record failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating medical record failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Retrieves a medical record by ID.
     * 
     * @param id The ID of the medical record to retrieve
     * @return The MedicalRecord object if found, null otherwise
     */
    public MedicalRecord getMedicalRecordById(int id) {
        String sql = "SELECT * FROM medical_records WHERE id = ?";
        
        try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                MedicalRecord record = new MedicalRecord();
                record.setId(resultSet.getInt("id"));
                record.setPatientId(resultSet.getInt("patient_id"));
                record.setVisitDate(resultSet.getDate("visit_date"));
                record.setDiagnosis(resultSet.getString("diagnosis"));
                record.setTreatmentPlan(resultSet.getString("treatment_plan"));
                return record;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    /**
     * Updates an existing medical record in the database.
     * 
     * @param record The MedicalRecord object with updated information
     * @return true if update was successful, false otherwise
     */
    public boolean updateMedicalRecord(MedicalRecord record) {
        String sql = "UPDATE medical_records SET patient_id = ?, visit_date = ?, " +
                     "diagnosis = ?, treatment_plan = ? WHERE id = ?";
        
        try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            statement.setInt(1, record.getPatientId());
            statement.setDate(2, record.getVisitDate());
            statement.setString(3, record.getDiagnosis());
            statement.setString(4, record.getTreatmentPlan());
            statement.setInt(5, record.getId());
            
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a medical record from the database.
     * 
     * @param id The ID of the medical record to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteMedicalRecord(int id) {
        String sql = "DELETE FROM medical_records WHERE id = ?";
        
        try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            statement.setInt(1, id);
            
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Gets all medical records for a specific patient.
     * 
     * @param patientId The ID of the patient
     * @return A list of all medical records for the patient
     */
    public List<MedicalRecord> getMedicalRecordsByPatientId(int patientId) {
        List<MedicalRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM medical_records WHERE patient_id = ?";
        
        try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            statement.setInt(1, patientId);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                MedicalRecord record = new MedicalRecord();
                record.setId(resultSet.getInt("id"));
                record.setPatientId(resultSet.getInt("patient_id"));
                record.setVisitDate(resultSet.getDate("visit_date"));
                record.setDiagnosis(resultSet.getString("diagnosis"));
                record.setTreatmentPlan(resultSet.getString("treatment_plan"));
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return records;
    }

    /**
     * Gets all medical records from the database.
     * 
     * @return A list of all medical records
     */
    public List<MedicalRecord> getAllMedicalRecords() {
        List<MedicalRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM medical_records";
        
        try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                MedicalRecord record = new MedicalRecord();
                record.setId(resultSet.getInt("id"));
                record.setPatientId(resultSet.getInt("patient_id"));
                record.setVisitDate(resultSet.getDate("visit_date"));
                record.setDiagnosis(resultSet.getString("diagnosis"));
                record.setTreatmentPlan(resultSet.getString("treatment_plan"));
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return records;
    }

    /**
     * Searches for medical records containing specific diagnosis text.
     * 
     * @param diagnosisText Text to search for in diagnosis
     * @return A list of matching medical records
     */
    public List<MedicalRecord> searchMedicalRecordsByDiagnosis(String diagnosisText) {
        List<MedicalRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM medical_records WHERE diagnosis LIKE ?";
        
        try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            String searchPattern = "%" + diagnosisText + "%";
            statement.setString(1, searchPattern);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                MedicalRecord record = new MedicalRecord();
                record.setId(resultSet.getInt("id"));
                record.setPatientId(resultSet.getInt("patient_id"));
                record.setVisitDate(resultSet.getDate("visit_date"));
                record.setDiagnosis(resultSet.getString("diagnosis"));
                record.setTreatmentPlan(resultSet.getString("treatment_plan"));
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return records;
    }
}