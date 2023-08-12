package online.epochsolutions.eticketor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ETicketorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ETicketorApplication.class, args);
    }

}
