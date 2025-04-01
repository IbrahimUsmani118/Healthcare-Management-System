package com.medicalapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Patient class.
 */
public class PatientTest {
    
    private Patient patient;
    private static final int TEST_ID = 1;
    private static final String TEST_FIRST_NAME = "John";
    private static final String TEST_LAST_NAME = "Doe";
    private static final Date TEST_BIRTHDATE = Date.valueOf("1990-01-01");
    private static final String TEST_GENDER = "Male";
    private static final String TEST_ADDRESS = "123 Main St";

    @BeforeEach
    public void setUp() {
        // Initialize a new Patient object before each test
        patient = new Patient();
    }

    @AfterEach
    public void tearDown() {
        // Clean up after each test
        patient = null;
    }

    @Test
    @DisplayName("Test Patient constructor with parameters")
    public void testPatientConstructorWithParameters() {
        Patient patientWithParams = new Patient(TEST_ID, TEST_FIRST_NAME, TEST_LAST_NAME, TEST_BIRTHDATE, TEST_GENDER, TEST_ADDRESS);
        
        assertEquals(TEST_ID, patientWithParams.getId());
        assertEquals(TEST_FIRST_NAME, patientWithParams.getFirstName());
        assertEquals(TEST_LAST_NAME, patientWithParams.getLastName());
        assertEquals(TEST_BIRTHDATE, patientWithParams.getBirthdate());
        assertEquals(TEST_GENDER, patientWithParams.getGender());
        assertEquals(TEST_ADDRESS, patientWithParams.getAddress());
    }

    @Test
    @DisplayName("Test setting and getting patient ID")
    public void testSetAndGetId() {
        patient.setId(TEST_ID);
        assertEquals(TEST_ID, patient.getId());
    }

    @Test
    @DisplayName("Test setting and getting first name")
    public void testSetAndGetFirstName() {
        patient.setFirstName(TEST_FIRST_NAME);
        assertEquals(TEST_FIRST_NAME, patient.getFirstName());
    }

    @Test
    @DisplayName("Test setting and getting last name")
    public void testSetAndGetLastName() {
        patient.setLastName(TEST_LAST_NAME);
        assertEquals(TEST_LAST_NAME, patient.getLastName());
    }

    @Test
    @DisplayName("Test setting and getting birthdate")
    public void testSetAndGetBirthdate() {
        patient.setBirthdate(TEST_BIRTHDATE);
        assertEquals(TEST_BIRTHDATE, patient.getBirthdate());
    }

    @Test
    @DisplayName("Test setting and getting gender")
    public void testSetAndGetGender() {
        patient.setGender(TEST_GENDER);
        assertEquals(TEST_GENDER, patient.getGender());
    }

    @Test
    @DisplayName("Test setting and getting address")
    public void testSetAndGetAddress() {
        patient.setAddress(TEST_ADDRESS);
        assertEquals(TEST_ADDRESS, patient.getAddress());
    }

    @Test
    @DisplayName("Test toString method")
    public void testToString() {
        Patient patient = new Patient(TEST_ID, TEST_FIRST_NAME, TEST_LAST_NAME, TEST_BIRTHDATE, TEST_GENDER, TEST_ADDRESS);
        String expected = "Patient{id=1, firstName='John', lastName='Doe', birthdate=1990-01-01, gender='Male', address='123 Main St'}";
        assertEquals(expected, patient.toString());
    }
}