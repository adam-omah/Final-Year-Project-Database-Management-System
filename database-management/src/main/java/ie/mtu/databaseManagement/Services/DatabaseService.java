package ie.mtu.databaseManagement.Services;


import ie.mtu.databaseManagement.Entities.Database;
import ie.mtu.databaseManagement.Repositories.DatabaseRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        updatedDatabaseCopy.setDatabaseType(
                Optional.ofNullable(updatedDatabase.getDatabaseType())
                        .orElse(updatedDatabaseCopy.getDatabaseType()));
        updatedDatabaseCopy.setConnectionString(
                Optional.ofNullable(updatedDatabase.getConnectionString())
                        .orElse(updatedDatabaseCopy.getConnectionString()));
        updatedDatabaseCopy.setConnectionPassword(
                Optional.ofNullable(updatedDatabase.getConnectionPassword())
                        .orElse(updatedDatabaseCopy.getConnectionPassword()));
        updatedDatabaseCopy.setStatus(
                Optional.ofNullable(updatedDatabase.getStatus())
                        .orElse(updatedDatabaseCopy.getStatus()));
        updatedDatabaseCopy.setLast_update(
                Optional.ofNullable(updatedDatabase.getLast_update())
                        .orElse(updatedDatabaseCopy.getLast_update()));

        //Crucially, update the databaseID. This prevents inconsistencies
        updatedDatabaseCopy.setDatabaseId(existingDatabase.getDatabaseId());

        return databaseRepository.save(updatedDatabaseCopy);
    }

    // Delete operation
    public void deleteDatabaseById(Long databaseId)
    {
        databaseRepository.deleteById(databaseId);
    }
}