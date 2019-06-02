package com.kiselev.suggester.suggestion;

import com.kiselev.suggester.suggestion.neuro.NeuroSuggestionConfiguration;
import com.kiselev.suggester.suggestion.standard.StandardSuggestionConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({StandardSuggestionConfiguration.class, NeuroSuggestionConfiguration.class})
public class SuggesterConfiguration {
}
