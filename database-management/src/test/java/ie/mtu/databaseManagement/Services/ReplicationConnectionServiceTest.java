package ie.mtu.databaseManagement.Services;

import ie.mtu.databaseManagement.Entities.ReplicationConnection;
import ie.mtu.databaseManagement.Repositories.ReplicationConnectionRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
class ReplicationConnectionServiceTest {
    @Autowired
    private ReplicationConnectionService replicationConnectionService;
    @Autowired
    private ReplicationConnectionRepo replicationConnectionRepository;

    @Test
    @Sql(scripts = "classpath:SQL_Scripts/test-data.sql", executionPhase = BEFORE_TEST_METHOD)
    void saveReplicationConnection() {
        ReplicationConnection replicationConnection = new ReplicationConnection();
        replicationConnection.setConnectionName("Test Connection");
        replicationConnection.setStartDatabase(1L);
        replicationConnection.setEndDatabase(2L);
        replicationConnection.setStatus("ACTIVE");
        replicationConnection.setStartDate(new Date(System.currentTimeMillis()));


        ReplicationConnection savedConnection = replicationConnectionService.saveReplicationConnection(replicationConnection);

        assertNotNull(savedConnection);
        assertNotNull(savedConnection.getConnectionId());
        assertEquals("Test Connection", savedConnection.getConnectionName());
    }


    @Test
    @Sql(scripts = "classpath:SQL_Scripts/test-data.sql", executionPhase = BEFORE_TEST_METHOD)
    void fetchReplicationConnectionList() {
        List<ReplicationConnection> connections = replicationConnectionService.fetchReplicationConnectionList();
        assertFalse(connections.isEmpty());
    }

    @Test
    @Sql(scripts = "classpath:SQL_Scripts/test-data.sql", executionPhase = BEFORE_TEST_METHOD)
    void updateReplicationConnection() {
        ReplicationConnection existingConnection = replicationConnectionRepository.findById(1L).orElseThrow();
        assertNotNull(existingConnection);

        ReplicationConnection updatedConnection = new ReplicationConnection();
        updatedConnection.setConnectionId(existingConnection.getConnectionId());
        updatedConnection.setConnectionName("Updated Connection");
        updatedConnection.setStatus("INACTIVE");

        ReplicationConnection updatedResult = replicationConnectionService.updateReplicationConnection(updatedConnection, existingConnection.getConnectionId());
        assertEquals("Updated Connection", updatedResult.getConnectionName());
        assertEquals("INACTIVE", updatedResult.getStatus());
    }

    @Test
    @Sql(scripts = "classpath:SQL_Scripts/test-data.sql", executionPhase = BEFORE_TEST_METHOD)
    void deleteReplicationConnectionById() {
        Long connectionId = 1L;
        assertTrue(replicationConnectionRepository.existsById(connectionId));

        replicationConnectionService.deleteReplicationConnectionById(connectionId);
        assertFalse(replicationConnectionRepository.existsById(connectionId));
    }
}