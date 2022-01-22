package project.automate_me.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.automate_me.model.User;
import project.automate_me.repository.UserRepository;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {

        return args -> {
            //log.info("Preloading " + repository.save(new User("Tudor","admin", "admin")));
            //log.info("Preloading " + repository.save(new User("test","test", "owner")));

        };
    }
}