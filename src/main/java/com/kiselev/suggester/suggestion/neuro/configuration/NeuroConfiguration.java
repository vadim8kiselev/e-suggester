package com.kiselev.suggester.suggestion.neuro.configuration;

import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.suggestion.Suggester;
import com.kiselev.suggester.suggestion.neuro.NeuroAgent;
import com.kiselev.suggester.suggestion.neuro.function.NeuroFunction;
import com.kiselev.suggester.suggestion.neuro.function.entity.ProductNeuroFunction;
import com.kiselev.suggester.suggestion.neuro.function.entity.ProfileNeuroFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@org.springframework.context.annotation.Profile("neuro")
public class NeuroConfiguration {

    @Bean
    public Suggester suggester() {
        return new NeuroAgent();
    }

    @Bean
    public NeuroFunction<Profile> profileNeuroFunction() {
        return new ProfileNeuroFunction();
    }

    @Bean
    public NeuroFunction<Product> productNeuroFunction() {
        return new ProductNeuroFunction();
    }
}
