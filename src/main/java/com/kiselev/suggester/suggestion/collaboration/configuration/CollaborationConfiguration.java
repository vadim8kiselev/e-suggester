package com.kiselev.suggester.suggestion.collaboration.configuration;

import com.kiselev.suggester.suggestion.Suggester;
import com.kiselev.suggester.suggestion.collaboration.CollaborationSuggester;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("collaboration")
public class CollaborationConfiguration {

    @Bean
    public Suggester suggester() {
        return new CollaborationSuggester();
    }
}
