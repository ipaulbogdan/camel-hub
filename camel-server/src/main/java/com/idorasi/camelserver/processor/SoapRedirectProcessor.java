package com.idorasi.camelserver.processor;

import com.idorasi.camelserver.config.SoapClient;
import com.idorasi.camelserver.dto.SoapHelloWorldBody;
import com.idorasi.camelserver.generated.ObjectFactory;
import lombok.AllArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SoapRedirectProcessor implements Processor {

    private SoapClient soapClient;

    @Override
    public void process(Exchange exchange) {
        var objectFactory = new ObjectFactory();

        var request = objectFactory.createHelloWorldRequest();
        request.setName("redirected");

        var response = soapClient.postHello(request);

        exchange.getIn().setBody(response.getMessage());
    }
}
