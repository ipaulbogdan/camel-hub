package com.idorasi.camelserver.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@Setter
@Getter
@ConfigurationProperties(prefix = "rest-producer")
public class RestProducerTemplateConfig {

    private String baseUri;
    private String helloWorldPath;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .rootUri(baseUri)
                .build();
    }
}
