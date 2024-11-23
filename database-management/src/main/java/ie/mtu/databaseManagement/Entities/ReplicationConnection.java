package ie.mtu.databaseManagement.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

// Class
public class ReplicationConnection {
    @Id
    @SequenceGenerator(name = "Replication_Connection_seq", sequenceName = "Replication_Connection_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Replication_Connection_seq")
    private Long connectionId;
    private String connectionName;
    private Long startDatabase;
    private Long endDatabase;
    private String status;
    private Date startDate;
    private Date lastUsed;
}