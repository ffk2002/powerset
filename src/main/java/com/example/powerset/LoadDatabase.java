package com.example.powerset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Date;


@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PSetRepository repo){

        System.out.println(new Date() + "   =0==00=0=0=0=0==0=000=0=0=0=0==00=0=0=0=0=0=0=0=0=00=0=0=0=0=0=0=" + LocalDate.now());
        return args -> {
            log.info("Preloading " + repo.save(new PSet("dead", 10L, 275L, LocalDate.now())));
            log.info("Preloading " + repo.save(new PSet("dead", 5L,365L, LocalDate.now())));
            log.info("Preloading " + repo.save(new PSet("bench", 5L, 185L)));
        };
    }
}
