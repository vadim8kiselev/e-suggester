package com.kiselev.suggester.suggestion;

import com.kiselev.suggester.suggestion.collaboration.configuration.CollaborationConfiguration;
import com.kiselev.suggester.suggestion.content.configuration.ContentBasedConfiguration;
import com.kiselev.suggester.suggestion.neuro.configuration.NeuroConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CollaborationConfiguration.class, ContentBasedConfiguration.class, NeuroConfiguration.class})
public class SuggesterConfiguration {
}
