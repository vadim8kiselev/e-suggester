package com.kiselev.suggester.data.source.db;

import com.kiselev.suggester.data.source.db.dao.DAO;
import com.kiselev.suggester.data.source.db.dao.implementation.DAOService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
public class DBConfiguration {

    @Bean
    public DAO dao() {
        return new DAOService();
    }
}
