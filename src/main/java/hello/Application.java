package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Class fo starting {@link SpringBootApplication}
 */
@SpringBootApplication
public class Application {


    /**
     * Start point for running {@link Application}
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
