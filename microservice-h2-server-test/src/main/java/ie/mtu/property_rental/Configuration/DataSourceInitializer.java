package ie.mtu.property_rental.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
//
//@Component
//public class DataSourceInitializer implements CommandLineRunner {
//
//    private static final Logger logger = LoggerFactory.getLogger(DataSourceInitializer.class);
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Value("classpath:src/main/resources/schema.sql") // Path to your schema script
//    private Resource schemaScript;
//
//
//    @Override
//    public void run(String... args) throws Exception {
//        try (Connection connection = dataSource.getConnection()) {
//            ScriptUtils.executeSqlScript(connection, schemaScript);
//            logger.info("Database initialization scripts executed successfully.");
//        } catch (SQLException e) {
//            logger.error("Failed to initialize database: {}", e.getMessage(), e);
//            throw new RuntimeException("Database initialization failed", e);
//        }
//    }
//}
