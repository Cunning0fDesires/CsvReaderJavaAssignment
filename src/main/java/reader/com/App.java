package reader.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reader.com.util.ConsoleInterface;

/**
 * Hello world!
 *
 */

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        ConsoleInterface consoleInterface = new ConsoleInterface();
        consoleInterface.start();
    }
}
