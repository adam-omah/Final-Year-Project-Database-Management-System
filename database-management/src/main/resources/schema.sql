CREATE TABLE IF NOT EXISTS Database (
    database_id BIGINT PRIMARY KEY,
    database_name VARCHAR(255) NOT NULL,
    database_type VARCHAR(255) NOT NULL,
    connection_string VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    connection_password VARCHAR(120) NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
    last_update DATE
    );

CREATE SEQUENCE IF NOT EXISTS Database_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS Replication_Connection (
    connection_id BIGINT PRIMARY KEY,
    connection_name varchar(255),
    start_database BIGINT,
    end_database BIGINT,
    status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
    start_date DATE,
    last_used DATE,
    FOREIGN KEY (start_database) REFERENCES Database(database_id),
    FOREIGN KEY (end_database) REFERENCES Database(database_id)
    );

CREATE SEQUENCE IF NOT EXISTS Replication_Connection_SEQ START WITH 1 INCREMENT BY 1;