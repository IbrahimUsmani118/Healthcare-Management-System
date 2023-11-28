import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class DatabaseDataInsertion {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/healthcare_db";
        String username = "your_username";
        String password = "$Usmani1"; // Change the password

        try {
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            Statement statement = connection.createStatement();

            // Call a method to insert data into the tables
            insertData(statement);

            System.out.println("Data inserted successfully.");

            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to insert data into existing tables
    public static void insertData(Statement statement) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Insert Patient Data:");

            System.out.print("First Name: ");
            String firstName = scanner.nextLine();

            System.out.print("Last Name: ");
            String lastName = scanner.nextLine();

            System.out.print("Birthdate (YYYY-MM-DD): ");
            String birthdate = scanner.nextLine();

            System.out.print("Gender (Male/Female/Other): ");
            String gender = scanner.nextLine();

            System.out.print("Address: ");
            String address = scanner.nextLine();

            // Insert data into the Patients table
            String insertPatientsData = "INSERT INTO Patients (first_name, last_name, birthdate, gender, address) VALUES "
                    + "('" + firstName + "', '" + lastName + "', '" + birthdate + "', '" + gender + "', '" + address + "');";
            statement.executeUpdate(insertPatientsData);

            // Insert data into other tables as needed

            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
