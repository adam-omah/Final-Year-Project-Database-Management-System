DROP Table if exists Replication_Connection;
drop table if exists Database;

drop sequence if exists Replication_Connection_SEQ;
drop sequence if exists Database_SEQ;


-- Create Database table
CREATE TABLE IF NOT EXISTS Database (
    database_id BIGINT PRIMARY KEY,
    database_name VARCHAR(255) NOT NULL,
    database_type VARCHAR(255) NOT NULL,
    connection_string VARCHAR(255) NOT NULL,
    connection_password VARCHAR(120) NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
    last_update TIMESTAMP -- Using TIMESTAMP for better date/time handling
    );

CREATE SEQUENCE IF NOT EXISTS Database_SEQ START WITH 1 INCREMENT BY 1;


-- Create Replication_Connection table
CREATE TABLE IF NOT EXISTS Replication_Connection (
    connection_id BIGINT PRIMARY KEY,
    connection_name varchar(255),
    start_database BIGINT,
    end_database BIGINT,
    status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
    start_date TIMESTAMP,
    last_used TIMESTAMP,
    FOREIGN KEY (start_database) REFERENCES Database(database_id),
    FOREIGN KEY (end_database) REFERENCES Database(database_id)
    );

CREATE SEQUENCE IF NOT EXISTS Replication_Connection_SEQ START WITH 1 INCREMENT BY 1;

INSERT INTO Database (database_id, database_type, database_name, connection_string, connection_password, last_update) VALUES
    (nextval('Database_SEQ'), 'PostgreSQL', 'postgres_db', 'jdbc:postgresql://localhost:5432/mydatabase', 'mysecretpassword', CURRENT_TIMESTAMP);

INSERT INTO Database (database_id, database_type, database_name, connection_string, connection_password, last_update) VALUES
    (nextval('Database_SEQ'), 'MySQL', 'mysql_db1', 'jdbc:mysql://localhost:3306/mydatabase', 'anothersecret', CURRENT_TIMESTAMP);

INSERT INTO Database (database_id, database_type, database_name, connection_string, connection_password, last_update) VALUES
    (nextval('Database_SEQ'), 'MySQL', 'mysql_db2', 'jdbc:mysql://localhost:3306/mydatabase', 'anothersecret', CURRENT_TIMESTAMP);

INSERT INTO Database (database_id, database_type, database_name, connection_string, connection_password, last_update) VALUES
    (nextval('Database_SEQ'), 'H2', 'h2_db', 'jdbc:h2:mem:testdb', 'sa', CURRENT_TIMESTAMP);


-- Insert sample data into Replication_Connection table
INSERT INTO Replication_Connection (connection_id, connection_name, start_database, end_database, status, start_date, last_used) VALUES
    (nextval('Replication_Connection_SEQ'), 'Connection 1', 1, 2, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO Replication_Connection (connection_id, connection_name, start_database, end_database, status, start_date, last_used) VALUES
    (nextval('Replication_Connection_SEQ'), 'Connection 2', 2, 3, 'INACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);