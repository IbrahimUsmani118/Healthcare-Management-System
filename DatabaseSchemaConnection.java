import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseSchemaConnection {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/healthcare_db";
        String username = "your_username";
        String password = "$Usmani1"; // Change the password

        try {
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            Statement statement = connection.createStatement();

            System.out.println("Database schema connection established.");

            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
