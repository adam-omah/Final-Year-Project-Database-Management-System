package ie.mtu.databaseManagement.Controller;

import ie.mtu.databaseManagement.Entities.Database;
import ie.mtu.databaseManagement.Services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Annotation
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/databases")
// Class
public class DatabaseController {

    @Autowired
    private DatabaseService databaseService;

    // Save operation
    @PostMapping("/databases")
    public Database saveDatabase(
            @RequestBody Database database)
    {
        return databaseService.saveDatabase(database);
    }

    // Read operation
    @GetMapping("/databases")
    public List<Database> fetchDatabaseList()
    {
        return databaseService.fetchDatabaseList();
    }

    // Update operation
    @PutMapping("/databases/{id}")
    public Database
    updateDatabase(@RequestBody Database database,
                     @PathVariable("id") Long databaseId)
    {
        return databaseService.updateDatabase(
                database, databaseId);
    }

    // Delete operation
    @DeleteMapping("/databases/{id}")
    public String deleteDatabaseById(@PathVariable("id")
                                       Long databaseId)
    {
        databaseService.deleteDatabaseById(
                databaseId);
        return "Deleted Successfully";
    }
}
