package com.idorasi.camelserver.processor;

import lombok.AllArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class HtmlProcessor implements Processor {

    public static final String CAMEL_HTTP_URI = "CamelHttpUri";
    public static final String CAMEL_HTTP_QUERY = "CamelHttpQuery";

    private RestTemplate htmlProducerRestTemplate;

    @Override
    public void process(Exchange exchange) {
        var path = exchange.getIn().getHeader(CAMEL_HTTP_URI, String.class);
        var queryParams = exchange.getIn().getHeader(CAMEL_HTTP_QUERY, String.class);
        var url = path + "?" + queryParams;

        var body = htmlProducerRestTemplate.getForObject(url, String.class);

        exchange.getIn().setBody(body);
    }
}
