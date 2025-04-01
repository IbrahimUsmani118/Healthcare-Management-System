package com.medicalapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages CRUD operations for Patient objects in the database.
 */
public class PatientManager {
    private Connection dbConnection;

    /**
     * Constructor that accepts a database connection.
     * 
     * @param dbConnection The database connection to use
     */
    public PatientManager(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    /**
     * Adds a new patient to the database.
     * 
     * @param patient The Patient object to add
     * @return The ID of the newly inserted patient
     */
    public int addPatient(Patient patient) {
        String sql = "INSERT INTO patients (first_name, last_name, birthdate, gender, address) " +
                     "VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement statement = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, patient.getFirstName());
            statement.setString(2, patient.getLastName());
            statement.setDate(3, patient.getBirthdate());
            statement.setString(4, patient.getGender());
            statement.setString(5, patient.getAddress());
            
            int affectedRows = statement.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Creating patient failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating patient failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Retrieves a patient by ID.
     * 
     * @param id The ID of the patient to retrieve
     * @return The Patient object if found, null otherwise
     */
    public Patient getPatientById(int id) {
        String sql = "SELECT * FROM patients WHERE id = ?";
        
        try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                Patient patient = new Patient();
                patient.setId(resultSet.getInt("id"));
                patient.setFirstName(resultSet.getString("first_name"));
                patient.setLastName(resultSet.getString("last_name"));
                patient.setBirthdate(resultSet.getDate("birthdate"));
                patient.setGender(resultSet.getString("gender"));
                patient.setAddress(resultSet.getString("address"));
                return patient;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    /**
     * Updates an existing patient in the database.
     * 
     * @param patient The Patient object with updated information
     * @return true if update was successful, false otherwise
     */
    public boolean updatePatient(Patient patient) {
        String sql = "UPDATE patients SET first_name = ?, last_name = ?, birthdate = ?, " +
                     "gender = ?, address = ? WHERE id = ?";
        
        try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            statement.setString(1, patient.getFirstName());
            statement.setString(2, patient.getLastName());
            statement.setDate(3, patient.getBirthdate());
            statement.setString(4, patient.getGender());
            statement.setString(5, patient.getAddress());
            statement.setInt(6, patient.getId());
            
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a patient from the database.
     * 
     * @param id The ID of the patient to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean deletePatient(int id) {
        String sql = "DELETE FROM patients WHERE id = ?";
        
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
     * Gets all patients from the database.
     * 
     * @return A list of all patients
     */
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients";
        
        try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Patient patient = new Patient();
                patient.setId(resultSet.getInt("id"));
                patient.setFirstName(resultSet.getString("first_name"));
                patient.setLastName(resultSet.getString("last_name"));
                patient.setBirthdate(resultSet.getDate("birthdate"));
                patient.setGender(resultSet.getString("gender"));
                patient.setAddress(resultSet.getString("address"));
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return patients;
    }

    /**
     * Searches for patients by name.
     * 
     * @param name Name to search for (first or last)
     * @return A list of matching patients
     */
    public List<Patient> searchPatientsByName(String name) {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients WHERE first_name LIKE ? OR last_name LIKE ?";
        
        try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            String searchPattern = "%" + name + "%";
            statement.setString(1, searchPattern);
            statement.setString(2, searchPattern);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Patient patient = new Patient();
                patient.setId(resultSet.getInt("id"));
                patient.setFirstName(resultSet.getString("first_name"));
                patient.setLastName(resultSet.getString("last_name"));
                patient.setBirthdate(resultSet.getDate("birthdate"));
                patient.setGender(resultSet.getString("gender"));
                patient.setAddress(resultSet.getString("address"));
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return patients;
    }
}