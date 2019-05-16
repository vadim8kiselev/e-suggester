package com.kiselev.suggester.network.facebook;

import com.kiselev.suggester.network.facebook.converter.FacebookEntityConverter;
import com.kiselev.suggester.network.facebook.implementation.FacebookAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("facebook")
public class FacebookConfiguration {

    @Bean
    public FacebookAPI facebookAPI() {
        return new FacebookAPI();
    }

    @Bean
    public FacebookEntityConverter facebookEntityConverter() {
        return new FacebookEntityConverter();
    }
}
