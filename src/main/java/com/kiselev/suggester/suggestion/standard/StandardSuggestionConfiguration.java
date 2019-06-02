package com.kiselev.suggester.suggestion.standard;

import com.kiselev.suggester.suggestion.standard.implementation.collaboration.CollaborationSuggester;
import com.kiselev.suggester.suggestion.standard.implementation.content.ContentBasedSuggester;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("standard")
public class StandardSuggestionConfiguration {

    @Bean
    public StandardSuggestionService standardSuggestionService() {
        return new StandardSuggestionService();
    }

    @Bean
    public ContentBasedSuggester contentBasedSuggester() {
        return new ContentBasedSuggester();
    }

    @Bean
    public CollaborationSuggester collaborationSuggester() {
        return new CollaborationSuggester();
    }
}
