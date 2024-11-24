package ie.mtu.databaseManagement.Services;


import ie.mtu.databaseManagement.Entities.Database;
import ie.mtu.databaseManagement.Repositories.DatabaseRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

// Annotation
@Service
public class DatabaseService {

    @Autowired
    private DatabaseRepo databaseRepository;

    // Save operation
    public Database saveDatabase(Database database)
    {
        return databaseRepository.save(database);
    }

    // Read All operation
    public List<Database> fetchDatabaseList()
    {
        return (List<Database>)
                databaseRepository.findAll();
    }


    public Database updateDatabase(Database updatedDatabase, Long databaseId) {
        Database existingDatabase = databaseRepository.findById(databaseId)
                .orElseThrow(() -> new IllegalArgumentException("Database not found with ID: " + databaseId));

        // Create a copy to avoid modifying the original
        Database updatedDatabaseCopy = new Database();
        BeanUtils.copyProperties(existingDatabase, updatedDatabaseCopy);

        // Update fields selectively, handling nulls and empty strings
        updatedDatabaseCopy.setDatabaseName(
                Optional.ofNullable(updatedDatabase.getDatabaseName())
                        .orElse(updatedDatabaseCopy.getDatabaseName()));
        updatedDatabaseCopy.setDatabaseType(
                Optional.ofNullable(updatedDatabase.getDatabaseType())
                        .orElse(updatedDatabaseCopy.getDatabaseType()));
        updatedDatabaseCopy.setConnectionString(
                Optional.ofNullable(updatedDatabase.getConnectionString())
                        .orElse(updatedDatabaseCopy.getConnectionString()));
        updatedDatabaseCopy.setUsername(
                Optional.ofNullable(updatedDatabase.getUsername())
                        .orElse(updatedDatabaseCopy.getUsername()));
        updatedDatabaseCopy.setConnectionPassword(
                Optional.ofNullable(updatedDatabase.getConnectionPassword())
                        .orElse(updatedDatabaseCopy.getConnectionPassword()));
        updatedDatabaseCopy.setStatus(
                Optional.ofNullable(updatedDatabase.getStatus())
                        .orElse(updatedDatabaseCopy.getStatus()));
        updatedDatabaseCopy.setLastUpdate(
                Optional.ofNullable(updatedDatabase.getLastUpdate())
                        .orElse(updatedDatabaseCopy.getLastUpdate()));

        //Crucially, update the databaseID. This prevents inconsistencies
        updatedDatabaseCopy.setDatabaseId(existingDatabase.getDatabaseId());

        return databaseRepository.save(updatedDatabaseCopy);
    }

    // Delete operation
    public void deleteDatabaseById(Long databaseId)
    {
        databaseRepository.deleteById(databaseId);
    }


    public String connectToDatabase(Database database) {
        String connectionString = database.getConnectionString();
        String password = database.getConnectionPassword();
        String user = database.getUsername();//add a username field to your database entity

        try (Connection connection = DriverManager.getConnection(connectionString, user, password)) {
            System.out.println("Successfully connected to the database.");
            return "Successfully connected to the database.";
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
            e.printStackTrace();//print the stacktrace for debugging
            return "Failed to connect to the database";
        }
    }
}