package org.testtask.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
class DaoFactory {

    private StoreRepository repository;
    private Logger logger;

    @Bean
    public CommandLineRunner initDatabase(StoreRepository repository) {
        return args -> {
            this.repository = repository;
            this.logger = LoggerFactory.getLogger(DaoFactory.class);
        };
    }

    public StoreRepository getStoreRepository() {
        return repository;
    }

}
