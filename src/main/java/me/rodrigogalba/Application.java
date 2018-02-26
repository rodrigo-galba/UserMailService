package me.rodrigogalba;

import me.rodrigogalba.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import me.rodrigogalba.repository.UserRepository;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

//    @Bean
    public CommandLineRunner init(UserRepository repository) {
        return (args) -> {
            // save a user
            User user = new User("Airton Senna", "senna", "senna@email.com", "plain_password");
            repository.save(user);

            // fetch all users
            log.info("users found with findAll():");
            log.info("-------------------------------");
            for (User u : repository.findAll()) {
                log.info(u.toString());
            }
            log.info("");

            // fetch an individual user by ID
            user = repository.findOne(2L);
            log.info("User found with findOne():");
            log.info("--------------------------------");
            log.info(user.toString());
            log.info("");

            Thread.sleep(3000);
            user.setName("Airton-updated");
            repository.save(user);

            User changedUser = repository.findOne(user.getId());
            log.info("User found with findOne():");
            log.info("--------------------------------");
            log.info(changedUser.toString());
            log.info("");

            Thread.sleep(3000);
            user.setName("Airton-updated2");
            repository.save(user);

            changedUser = repository.findOne(user.getId());
            log.info("User found with findOne():");
            log.info("--------------------------------");
            log.info(changedUser.toString());
            log.info("");
        };
    }

}
