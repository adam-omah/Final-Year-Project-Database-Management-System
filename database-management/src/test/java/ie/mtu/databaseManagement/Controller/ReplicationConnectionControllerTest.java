package ie.mtu.databaseManagement.Controller;

import ie.mtu.databaseManagement.Entities.ReplicationConnection;
import ie.mtu.databaseManagement.Services.ReplicationConnectionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
class ReplicationConnectionControllerTest {

    @Autowired
    private ReplicationConnectionController replicationConnectionController;

    @Autowired
    private ReplicationConnectionService replicationConnectionService;


    @Test
    @Sql(scripts = "classpath:SQL_Scripts/test-data.sql", executionPhase = BEFORE_TEST_METHOD)
    void saveReplicationConnection() {
        ReplicationConnection newConnection = new ReplicationConnection();
        newConnection.setConnectionName("Test Connection from controller");
        newConnection.setStartDatabase(1L);
        newConnection.setEndDatabase(2L);
        newConnection.setStatus("ACTIVE");
        newConnection.setStartDate(new Date(System.currentTimeMillis()));

        ReplicationConnection savedConnection = replicationConnectionController.saveReplicationConnection(newConnection);

        assertNotNull(savedConnection);
        assertNotNull(savedConnection.getConnectionId());
        assertEquals("Test Connection from controller", savedConnection.getConnectionName());
    }

    @Test
    @Sql(scripts = "classpath:SQL_Scripts/test-data.sql", executionPhase = BEFORE_TEST_METHOD)
    void fetchReplicationConnectionList() {
        List<ReplicationConnection> connections = replicationConnectionController.fetchReplicationConnectionList();
        assertNotNull(connections);
        assertFalse(connections.isEmpty());
    }


    @Test
    @Sql(scripts = "classpath:SQL_Scripts/test-data.sql", executionPhase = BEFORE_TEST_METHOD)
    void updateReplicationConnection() {
        ReplicationConnection existingConnection = replicationConnectionService.fetchReplicationConnectionList().get(0);

        ReplicationConnection updatedConnection = new ReplicationConnection();
        updatedConnection.setConnectionId(existingConnection.getConnectionId());
        updatedConnection.setConnectionName("Updated Connection from controller");
        updatedConnection.setStatus("INACTIVE");

        ReplicationConnection updated = replicationConnectionController.updateReplicationConnection(updatedConnection, existingConnection.getConnectionId());
        assertEquals("Updated Connection from controller", updated.getConnectionName());
        assertEquals("INACTIVE", updated.getStatus());

    }

    @Test
    @Sql(scripts = "classpath:SQL_Scripts/test-data.sql", executionPhase = BEFORE_TEST_METHOD)
    void deleteReplicationConnectionById() {
        ReplicationConnection existingConnection = replicationConnectionService.fetchReplicationConnectionList().get(0);
        Long connectionId = existingConnection.getConnectionId();

        String result = replicationConnectionController.deleteReplicationConnectionById(connectionId);

        assertEquals("Replication Connection deleted successfully", result);
    }
}