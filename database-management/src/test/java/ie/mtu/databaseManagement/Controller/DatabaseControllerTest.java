package ie.mtu.databaseManagement.Controller;

import ie.mtu.databaseManagement.Entities.Database;
import ie.mtu.databaseManagement.Services.DatabaseService;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
class DatabaseControllerTest {
    @Autowired
    private DatabaseController databaseController;

    @Autowired
    private DatabaseService databaseService;

    @Test
    @Sql(scripts = "classpath:SQL_Scripts/test-data.sql", executionPhase = BEFORE_TEST_METHOD)
    void saveDatabase() {
        Database newDatabase = new Database();
        newDatabase.setDatabaseName("test_db");
        newDatabase.setDatabaseType("PostgreSQL");
        newDatabase.setConnectionString("jdbc:postgresql://localhost:5432/mydatabase");
        newDatabase.setConnectionPassword("mysecretpassword");
        newDatabase.setStatus("ACTIVE");



        Database savedDatabase = databaseController.saveDatabase(newDatabase);

        assertNotNull(savedDatabase);
        assertEquals(newDatabase.getDatabaseName(), savedDatabase.getDatabaseName()); //Assert the name is correct
        assertNotNull(savedDatabase.getDatabaseId()); // Assert the id is not null
    }

    @Test
    @Sql(scripts = "classpath:SQL_Scripts/test-data.sql", executionPhase = BEFORE_TEST_METHOD)
    void fetchDatabaseList() {
        List<Database> databases = databaseController.fetchDatabaseList();

        assertNotNull(databases);
        assertTrue(!databases.isEmpty()); // Check that the list is not empty
    }

    @Test
    @Sql(scripts = "classpath:SQL_Scripts/test-data.sql", executionPhase = BEFORE_TEST_METHOD)
    void updateDatabase() {
        Optional<Database> existingDatabase = databaseService.fetchDatabaseList().stream().filter(db -> db.getDatabaseName().equals("mysql_db1")).findFirst();

        Database existing = existingDatabase.get();

        Database updatedDatabase = new Database();
        updatedDatabase.setDatabaseId(existing.getDatabaseId());
        updatedDatabase.setDatabaseName("updated_db");
        updatedDatabase.setDatabaseType("Updated Type"); // Set a new type


        Database updated = databaseController.updateDatabase(updatedDatabase, existing.getDatabaseId());

        assertNotNull(updated);
        assertEquals("updated_db", updated.getDatabaseName());
        assertEquals("Updated Type", updated.getDatabaseType()); //Assert the new type
    }

    @Test
    @Sql(scripts = "classpath:SQL_Scripts/test-data.sql", executionPhase = BEFORE_TEST_METHOD)
    void deleteDatabaseById() {
        Long databaseId = 1L;

        String result = databaseController.deleteDatabaseById(databaseId);

        assertEquals("Deleted Successfully", result);
    }
}