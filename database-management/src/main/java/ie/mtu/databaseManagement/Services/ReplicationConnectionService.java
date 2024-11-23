package ie.mtu.databaseManagement.Services;


import ie.mtu.databaseManagement.Entities.Database;
import ie.mtu.databaseManagement.Entities.ReplicationConnection;
import ie.mtu.databaseManagement.Repositories.DatabaseRepo;
import ie.mtu.databaseManagement.Repositories.ReplicationConnectionRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReplicationConnectionService {

    @Autowired
    private ReplicationConnectionRepo replicationConnectionRepository;


    public ReplicationConnection saveReplicationConnection(ReplicationConnection replicationConnection) {
        return replicationConnectionRepository.save(replicationConnection);
    }

    public List<ReplicationConnection> fetchReplicationConnectionList() {
        return replicationConnectionRepository.findAll();
    }

    public ReplicationConnection updateReplicationConnection(ReplicationConnection updatedReplicationConnection, Long connectionId) {
        ReplicationConnection existingReplicationConnection = replicationConnectionRepository.findById(connectionId)
                .orElseThrow(() -> new IllegalArgumentException("Replication Connection not found with ID: " + connectionId));

        // Update fields selectively, handling nulls
        existingReplicationConnection.setConnectionName(
                Optional.ofNullable(updatedReplicationConnection.getConnectionName())
                        .orElse(existingReplicationConnection.getConnectionName()));
        existingReplicationConnection.setStartDatabase(
                Optional.ofNullable(updatedReplicationConnection.getStartDatabase())
                        .orElse(existingReplicationConnection.getStartDatabase()));
        existingReplicationConnection.setEndDatabase(
                Optional.ofNullable(updatedReplicationConnection.getEndDatabase())
                        .orElse(existingReplicationConnection.getEndDatabase()));
        existingReplicationConnection.setStatus(
                Optional.ofNullable(updatedReplicationConnection.getStatus())
                        .orElse(existingReplicationConnection.getStatus()));
        existingReplicationConnection.setStartDate(
                Optional.ofNullable(updatedReplicationConnection.getStartDate())
                        .orElse(existingReplicationConnection.getStartDate()));
        existingReplicationConnection.setLastUsed(
                Optional.ofNullable(updatedReplicationConnection.getLastUsed())
                        .orElse(existingReplicationConnection.getLastUsed()));

        return replicationConnectionRepository.save(existingReplicationConnection);
    }


    public void deleteReplicationConnectionById(Long connectionId) {
        replicationConnectionRepository.deleteById(connectionId);
    }
}