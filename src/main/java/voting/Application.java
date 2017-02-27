package voting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by domas on 1/10/17.
 */

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
