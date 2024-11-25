package ie.mtu.property_rental.Configuration;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

//@Configuration
//public class H2ServerConfiguration {
//
//    private static final Logger logger = LoggerFactory.getLogger(H2ServerConfiguration.class);
//
//    @Value("${spring.h2.tcp.port:9092}") // Allow port customization
//    private int h2TcpPort;
//
//    @Bean(initMethod = "start", destroyMethod = "stop")
//    public Server h2Server() throws SQLException {
//        Server server = Server.createTcpServer(
//                "-tcp",
//                "-tcpAllowOthers",
//                "-tcpPort", String.valueOf(h2TcpPort)
//        ).start(); // Explicitly start the server here
//
//        logger.info("H2 TCP server started on port {}", h2TcpPort);
//        return server;
//    }
//}
