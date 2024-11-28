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
public class DatabaseServerConnectionTest {
    @Autowired
    private DatabaseService databaseService;
    @Autowired
    private DatabaseRepo databaseRepo;


    // This test can only be run when the Test Microservices h2 database is running. Shows the failing To connect.
    @Test
    public void testConnectToH2Database(){
        Database database = new Database();
        database.setConnectionString("jdbc:h2:tcp://localhost:8084/data/properties");
        database.setUsername("sa");
        database.setConnectionPassword("password");
        String result = databaseService.connectToDatabase(database);

        // Assert the connection result
        assertEquals("Successfully connected to the database.", result);
    }


}
