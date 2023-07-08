package com.idorasi.camelserver.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@Getter
@Setter
@ConfigurationProperties(prefix = "html-producer")
public class HtmlProducerConfig {

    private String baseUri;

    @Bean
    public RestTemplate htmlProducerRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .rootUri(baseUri)
                .build();
    }

}
