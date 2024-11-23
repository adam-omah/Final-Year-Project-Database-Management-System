package ie.mtu.databaseManagement.Controller;

import ie.mtu.databaseManagement.Entities.Database;
import ie.mtu.databaseManagement.Entities.ReplicationConnection;
import ie.mtu.databaseManagement.Services.DatabaseService;
import ie.mtu.databaseManagement.Services.ReplicationConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/replicationConnections")
public class ReplicationConnectionController {

    @Autowired
    private ReplicationConnectionService replicationConnectionService;

    @PostMapping("/")
    public ReplicationConnection saveReplicationConnection(@RequestBody ReplicationConnection replicationConnection) {
        return replicationConnectionService.saveReplicationConnection(replicationConnection);
    }

    @GetMapping("/")
    public List<ReplicationConnection> fetchReplicationConnectionList() {
        return replicationConnectionService.fetchReplicationConnectionList();
    }

    @PutMapping("/{connectionId}")
    public ReplicationConnection updateReplicationConnection(@RequestBody ReplicationConnection updatedReplicationConnection, @PathVariable Long connectionId) {
        return replicationConnectionService.updateReplicationConnection(updatedReplicationConnection, connectionId);
    }

    @DeleteMapping("/{connectionId}")
    public String deleteReplicationConnectionById(@PathVariable Long connectionId) {
        replicationConnectionService.deleteReplicationConnectionById(connectionId);
        return "Replication Connection deleted successfully";
    }
}
