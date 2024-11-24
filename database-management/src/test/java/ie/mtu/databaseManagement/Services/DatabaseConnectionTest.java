package ie.mtu.databaseManagement.Services;

import ie.mtu.databaseManagement.Entities.Database;
import ie.mtu.databaseManagement.Repositories.DatabaseRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
public class DatabaseConnectionTest {
    @Autowired
    private DatabaseService databaseService;
    @Autowired
    private DatabaseRepo databaseRepo;


    // This test can only be run when the Test Microservices h2 database is running.
//    @Test
//    public void testConnectToH2Database(){
//        Database database = new Database();
//        database.setConnectionString("jdbc:h2:tcp://localhost:8084/data/properties");
//        database.setUsername("sa");
//        database.setConnectionPassword("password");
//        String result = databaseService.connectToDatabase(database);
//
//        // Assert the connection result
//        assertEquals("Successfully connected to the database.", result);
//    }

    @Test
    void testConnectToH2Database2() {
        String databaseName = "data/properties";
        String jdbcUrl = "jdbc:h2:file:/data/properties";

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
