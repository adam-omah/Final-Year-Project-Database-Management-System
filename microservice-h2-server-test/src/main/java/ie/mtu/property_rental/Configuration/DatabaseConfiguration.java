//package ie.mtu.property_rental.Configuration;
//
//import com.zaxxer.hikari.HikariDataSource;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//
//@Configuration
//public class DatabaseConfiguration {
//
//    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfiguration.class);
//
//    @Autowired
//    private H2ServerConfiguration h2ServerConfiguration;
//
//    @Bean
//    public DataSource dataSource(
//            @Value("${spring.datasource.url}") String url,
//            @Value("${spring.datasource.username}") String username,
//            @Value("${spring.datasource.password}") String password,
//            @Value("${spring.datasource.driverClassName}") String driverClassName
//    ) {
//        try {
//            HikariDataSource dataSource = new HikariDataSource();
//            dataSource.setJdbcUrl(url);
//            dataSource.setUsername(username);
//            dataSource.setPassword(password);
//            dataSource.setDriverClassName(driverClassName);
//            logger.info("Datasource Initialized");
//            return dataSource;
//        } catch (Exception ex) {
//            logger.error("Error creating datasource", ex);
//            throw new RuntimeException("Failed to create datasource", ex);
//        }
//    }
//}