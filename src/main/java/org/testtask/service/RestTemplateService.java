package org.testtask.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateService {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
