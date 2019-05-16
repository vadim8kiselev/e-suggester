package com.kiselev.suggester.suggestion.content.configuration;

import com.kiselev.suggester.suggestion.Suggester;
import com.kiselev.suggester.suggestion.content.ContentBasedSuggester;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("content")
public class ContentBasedConfiguration {

    @Bean
    public Suggester suggester() {
        return new ContentBasedSuggester();
    }
}
