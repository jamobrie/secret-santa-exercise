package com.secretsanta.configuration;


import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public Mapper getMapper() {
        return DozerBeanMapperBuilder.buildDefault();
    }

}