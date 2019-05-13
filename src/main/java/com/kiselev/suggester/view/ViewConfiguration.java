package com.kiselev.suggester.view;

import com.kiselev.suggester.view.rest.RestViewConfiguration;
import com.kiselev.suggester.view.web.WebViewConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RestViewConfiguration.class, WebViewConfiguration.class})
public class ViewConfiguration {
}
