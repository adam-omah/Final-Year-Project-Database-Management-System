package ie.mtu.databaseManagement.Repositories;

import ie.mtu.databaseManagement.Entities.Database;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DatabaseRepo
        extends JpaRepository<Database, Long> {
}