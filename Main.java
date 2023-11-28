import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        Connection dbConnection = null;
        try {
            String jdbcURL = "jdbc:mysql://localhost:3306/healthcare_db";
            String dbUsername = "your_username";
            String dbPassword = "$Usmani1";

            dbConnection = DriverManager.getConnection(jdbcURL, dbUsername, dbPassword);
            System.out.println("Database connection established.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a new patient
        Patient newPatient = new Patient();
        newPatient.setFirstName("John");
        newPatient.setLastName("Doe");
        newPatient.setBirthdate(Date.valueOf("1980-05-15"));
        newPatient.setGender("Male");
        newPatient.setAddress("123 Main St, City");

        // Create a new medical record
        MedicalRecord newRecord = new MedicalRecord();
        newRecord.setPatientId(newPatient.getId()); // Assuming the patient has an ID
        newRecord.setVisitDate(new Date(System.currentTimeMillis()));
        newRecord.setDiagnosis("Example Diagnosis");
        newRecord.setTreatmentPlan("Treatment Plan Details");

        // Insert the new patient and medical record into the database
        PatientManager patientManager = new PatientManager(dbConnection);
        MedicalRecordManager recordManager = new MedicalRecordManager(dbConnection);

        int patientId = patientManager.addPatient(newPatient);
        newPatient.setId(patientId);

        newRecord.setPatientId(patientId);
        recordManager.addMedicalRecord(newRecord);

        // Retrieve patient and medical record
        Patient retrievedPatient = patientManager.getPatientById(patientId);
        MedicalRecord retrievedRecord = recordManager.getMedicalRecordById(newRecord.getId());

        // Display patient and medical record details
        System.out.println("Patient Information:");
        System.out.println("ID: " + retrievedPatient.getId());
        System.out.println("Name: " + retrievedPatient.getFirstName() + " " + retrievedPatient.getLastName());
        System.out.println("Birthdate: " + retrievedPatient.getBirthdate());
        System.out.println("Gender: " + retrievedPatient.getGender());
        System.out.println("Address: " + retrievedPatient.getAddress());

        System.out.println("\nMedical Record Information:");
        System.out.println("ID: " + retrievedRecord.getId());
        System.out.println("Patient ID: " + retrievedRecord.getPatientId());
        System.out.println("Visit Date: " + retrievedRecord.getVisitDate());
        System.out.println("Diagnosis: " + retrievedRecord.getDiagnosis());
        System.out.println("Treatment Plan: " + retrievedRecord.getTreatmentPlan());

   }
}
