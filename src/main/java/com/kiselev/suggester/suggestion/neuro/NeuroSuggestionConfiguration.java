package com.kiselev.suggester.suggestion.neuro;

import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.suggestion.neuro.implementation.NeuroSuggester;
import com.kiselev.suggester.suggestion.neuro.implementation.function.NeuroFunction;
import com.kiselev.suggester.suggestion.neuro.implementation.function.implementation.ProductNeuroFunction;
import com.kiselev.suggester.suggestion.neuro.implementation.function.implementation.ProfileNeuroFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@org.springframework.context.annotation.Profile("neuro")
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
    public NeuroFunction<Profile> profileNeuroFunction() {
        return new ProfileNeuroFunction();
    }

    @Bean
    public NeuroFunction<Product> productNeuroFunction() {
        return new ProductNeuroFunction();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
