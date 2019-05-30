package com.kiselev.suggester.suggestion.neuro;

import com.kiselev.suggester.suggestion.neuro.implementation.NeuroSuggester;
import com.kiselev.suggester.suggestion.neuro.implementation.function.NeuroFunction;
import com.kiselev.suggester.suggestion.neuro.implementation.function.implementation.ProfileNeuroFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("neuro")
public class NeuroSuggestionConfiguration {

    @Bean
    public NeuroSuggestionService neuroSuggestionService() {
        return new NeuroSuggestionService();
    }

    @Bean
    public NeuroSuggester neuroAgent() {
        return new NeuroSuggester();
    }

    @Bean
    public NeuroFunction profileNeuroFunction() {
        return new ProfileNeuroFunction();
    }
}
