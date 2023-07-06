package com.idorasi.camelserver.processor;

import java.util.Optional;
import com.idorasi.camelserver.config.RestTemplateConfig;
import com.idorasi.camelserver.dto.ApiTech;
import com.idorasi.camelserver.dto.RestHelloWorld;
import com.idorasi.camelserver.util.UrlFormatter;
import lombok.AllArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class RestComponentProcessor implements Processor {

    private UrlFormatter urlFormatter;
    private RestTemplateConfig restTemplateConfig;
    private RestTemplate restTemplate;

    @Override
    public void process(Exchange exchange) {
        var redirectUrl = exchange.getIn().getHeader("redirectUrl", String.class);
        var url = redirectUrl != null ? redirectUrl : restTemplateConfig.getHelloWorldPath();

        var response = Optional.ofNullable(restTemplate.getForObject(url, RestHelloWorld.class));
        var mappedResponse = response.map(this::formatUrl)
                .orElse(null);

        exchange.getIn().setHeader("redirectUrl", null);
        exchange.getIn().setBody(mappedResponse);
    }

    private RestHelloWorld formatUrl(RestHelloWorld restHelloWorld) {
        if (restHelloWorld.getRedirectUrl() != null) {
            return restHelloWorld.setRedirectUrl(urlFormatter.formatUrl(restHelloWorld.getRedirectUrl(),
                    restTemplateConfig.getBaseUri(), ApiTech.REST));
        }

        return restHelloWorld;
    }
}
