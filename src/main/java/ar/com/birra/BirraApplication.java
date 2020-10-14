package ar.com.birra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author Carlos Moreno
 */
@SpringBootApplication
@EnableCaching
public class BirraApplication {

    public static void main(String[] args) {
        SpringApplication.run(BirraApplication.class, args);
    }
}
