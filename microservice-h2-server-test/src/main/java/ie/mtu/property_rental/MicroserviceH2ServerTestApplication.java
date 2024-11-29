package ie.mtu.property_rental;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@SpringBootApplication
public class MicroserviceH2ServerTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceH2ServerTestApplication.class, args);
    }

}
