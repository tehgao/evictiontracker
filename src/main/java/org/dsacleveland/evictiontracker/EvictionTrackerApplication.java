package org.dsacleveland.evictiontracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class EvictionTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EvictionTrackerApplication.class, args);
    }

}
