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
public class Database {
    @Id
    @SequenceGenerator(name = "database_seq", sequenceName = "database_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "database_seq")
    private Long databaseId;
    private String databaseType;
    private String connectionString;
    private String connectionPassword;
    private String status;
    private Date last_update;
}