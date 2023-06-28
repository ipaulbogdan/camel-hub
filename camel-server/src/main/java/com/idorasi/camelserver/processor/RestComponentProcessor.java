package com.idorasi.camelserver.processor;

import com.idorasi.camelserver.config.RestTemplateConfig;
import lombok.AllArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class RestComponentProcessor implements Processor {

    private RestTemplateConfig restTemplateConfig;
    private RestTemplate restTemplate;

    @Override
    public void process(Exchange exchange) {
        var response = restTemplate.getForEntity(restTemplateConfig.getHelloWorldPath(), String.class);

        exchange.getIn().setBody(response.getBody());
    }
}
