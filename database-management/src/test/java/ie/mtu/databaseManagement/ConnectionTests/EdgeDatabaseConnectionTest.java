package ie.mtu.databaseManagement.ConnectionTests;

import ie.mtu.databaseManagement.Entities.Database;
import ie.mtu.databaseManagement.Repositories.DatabaseRepo;
import ie.mtu.databaseManagement.Services.DatabaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class EdgeDatabaseConnectionTest {
    @Autowired
    private DatabaseService databaseService;
    @Autowired
    private DatabaseRepo databaseRepo;

    // This test can only be run when the Test Microservices h2 database Server is running.
    @Test
    void testConnectToH2DatabaseServer() {
        String databaseName = "data/properties";
        String jdbcUrl = "jdbc:h2:tcp://localhost:9093/mem:mydb";

        Database database = new Database();
        database.setConnectionString(jdbcUrl);
        database.setUsername("sa");
        database.setConnectionPassword("password");

        try {
            String result = databaseService.connectToDatabase(database);
            assertEquals("Successfully connected to the database.", result);
            // ... rest of your test
        } catch (Exception e) {
            System.err.println("Test failed with error: " + e.getMessage());
            e.printStackTrace(); // Print the full stack trace for debugging
            fail("Database connection failed: " + e.getMessage()); // JUnit failure
        }
    }


}