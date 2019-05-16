package com.kiselev.suggester.network.vk;

import com.kiselev.suggester.network.vk.aspect.VKCallTimeoutAspect;
import com.kiselev.suggester.network.vk.aspect.VKHandleAPIExceptionAspect;
import com.kiselev.suggester.network.vk.configuration.VKAPIConfiguration;
import com.kiselev.suggester.network.vk.converter.implementation.VKEntityConverter;
import com.kiselev.suggester.network.vk.implementation.VKAPI;
import com.kiselev.suggester.network.vk.implementation.internal.VKAPIInternalProvider;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("vk")
@EnableCaching
@Configuration
public class VKConfiguration {

    @Bean
    public VKAPIConfiguration vkapiConfiguration() {
        return new VKAPIConfiguration();
    }

    @Bean
    @Primary
    public VKAPI vkapi() {
        return new VKAPI();
    }

    @Bean
    public VKAPIInternalProvider vkapiInternal() {
        return new VKAPIInternalProvider();
    }

    @Bean
    public VKEntityConverter vkEntityConverter() {
        return new VKEntityConverter();
    }

    @Bean
    public VKCallTimeoutAspect vkCallAPIAspect() {
        return new VKCallTimeoutAspect();
    }

    @Bean
    public VKHandleAPIExceptionAspect vkHandleAPIExceptionAspect() {
        return new VKHandleAPIExceptionAspect();
    }
}
