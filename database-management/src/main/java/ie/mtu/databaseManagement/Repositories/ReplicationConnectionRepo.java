package ie.mtu.databaseManagement.Repositories;

import ie.mtu.databaseManagement.Entities.ReplicationConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReplicationConnectionRepo
        extends JpaRepository<ReplicationConnection, Long> {
}