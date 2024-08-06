package com.api_consumer.config;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class Config {

    private final static String GIT_URL = "https://api.github.com";

    @Bean
    public WebClient webClient(final WebClient.Builder builder){
        return builder.baseUrl(GIT_URL).build();
    }
}
