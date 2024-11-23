package ie.mtu.databaseManagement.Services;

import ie.mtu.databaseManagement.Entities.Database;
import ie.mtu.databaseManagement.Repositories.DatabaseRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
class DatabaseServiceTest {

    @Autowired
    private DatabaseService databaseService;
    @Autowired
    private DatabaseRepo databaseRepo;


    @Test
    @Sql(scripts = "classpath:SQL_Scripts/test-data.sql", executionPhase = BEFORE_TEST_METHOD)
    void saveDatabase() {
        Database database = new Database();
        database.setDatabaseName("testDB");
        database.setDatabaseType("PostgreSQL");
        database.setConnectionString("jdbc:postgresql://localhost:5432/mydatabase");
        database.setUsername("John");
        database.setConnectionPassword("mysecretpassword");
        database.setStatus("ACTIVE");

        Database savedDatabase = databaseService.saveDatabase(database);

        assertNotNull(savedDatabase);
        assertEquals(database.getDatabaseType(), savedDatabase.getDatabaseType());
    }

    @Test
    @Sql(scripts = "classpath:SQL_Scripts/test-data.sql", executionPhase = BEFORE_TEST_METHOD)
    void fetchDatabaseList() {
        List<Database> databaseList = databaseService.fetchDatabaseList();

        assertFalse(databaseList.isEmpty()); // Check if the list is not empty
    }

    @Test
    @Sql(scripts = "classpath:SQL_Scripts/test-data.sql", executionPhase = BEFORE_TEST_METHOD)
    void updateDatabase() {
        Database existingDatabase = databaseRepo.findById(1L).orElseThrow(() -> new IllegalArgumentException("Database not found"));
        assertNotNull(existingDatabase);


        Database updatedDatabase = new Database();
        BeanUtils.copyProperties(existingDatabase, updatedDatabase);
        updatedDatabase.setDatabaseType("MySQL");

        Database updatedDatabaseResult = databaseService.updateDatabase(updatedDatabase, 1L);
        assertEquals("MySQL", updatedDatabaseResult.getDatabaseType());
    }

    @Test
    @Sql(scripts = "classpath:SQL_Scripts/test-data.sql", executionPhase = BEFORE_TEST_METHOD)
    void deleteDatabaseById() {
        Long databaseId = 4L;
        assertTrue(databaseRepo.existsById(databaseId));

        databaseService.deleteDatabaseById(databaseId);

        assertFalse(databaseRepo.existsById(databaseId));
    }
}